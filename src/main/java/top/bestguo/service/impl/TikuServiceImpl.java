package top.bestguo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.bestguo.entity.Question;
import top.bestguo.mapper.QuestionMapper;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.TikuService;
import top.bestguo.vo.QuestionCondition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题库服务实现类
 */
@Service("tikuService")
public class TikuServiceImpl implements TikuService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 添加题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 添加成功与否
     */
    @Override
    public BaseResult addQuestion(Question question) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.insert(question);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目添加成功");
        } else {
            result.setCode(1);
            result.setMessage("题目添加失败");
        }
        return result;
    }

    /**
     * 修改题目到班级的题库中
     *
     * @param question 问题实体类
     * @return 修改成功与否
     */
    @Override
    public BaseResult modifyQuestion(Question question) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.updateById(question);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目修改成功");
        } else {
            result.setCode(1);
            result.setMessage("题目修改失败，该题目不存在");
        }
        return result;
    }

    /**
     * 删除题目到班级的题库中
     *
     * @param questionId 问题实体类
     * @return 删除成功与否
     */
    @Override
    public BaseResult deleteQuestion(Integer questionId) {
        BaseResult result = new BaseResult();
        int insert = questionMapper.deleteById(questionId);
        if(insert > 0) {
            result.setCode(0);
            result.setMessage("题目删除成功");
        } else {
            result.setCode(1);
            result.setMessage("题目删除失败，该题目不存在");
        }
        return result;
    }

    /**
     * 查询一个题目
     *
     * @param questionId 问题id
     * @return 删除成功与否
     */
    @Override
    public Question findQuestionById(Integer questionId) {
        return questionMapper.selectById(questionId);
    }

    /**
     * 查询当前班级中的全部题目
     *
     * @param classId 班级id
     * @return 多个问题记录和状态
     */
    @Override
    public MultipleDataResult<Question> findAllQuestion(Integer classId) {
        MultipleDataResult<Question> result = new MultipleDataResult<>();
        // 设置查询条件
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("belongclass", classId);
        // 查询问题
        List<Question> questions = questionMapper.selectList(questionQueryWrapper);

        return getQuestionMultipleDataResult(questions, null);
    }

    /**
     * 查询当前班级中的题目，带分页
     *
     * @param question 问题实体类
     * @param p 当前页数
     * @param limit 当前页展示的数据条数
     * @return 当前页的数据
     */
    @Override
    public MultipleDataResult<Question> findAllQuestion(QuestionCondition question, Integer p, Integer limit) {
        MultipleDataResult<Question> result = new MultipleDataResult<>();
        // 设置查询条件
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("belongclass", question.getBelongclass());
        // 按问题名模糊查询
        String questionname = question.getQuestionname();
        if(questionname != null && !"".equals(questionname)){
            questionQueryWrapper.like("questionname", questionname);
        }
        // 按问题难度系数查询
        String levels = question.getLevels();
        if(levels != null && !"".equals(levels)){
            // 切分字符串
            String[] levelArr = levels.split(",");
            // 保存
            Integer[] integers = new Integer[levelArr.length];
            for (int i = 0; i < integers.length; i++) {
                integers[i] = new Integer(levelArr[i]);
            }
            questionQueryWrapper.in("level", Arrays.asList(integers));
        }
        // 按选择类型查询
        Boolean ismulti = question.getIsmulti();
        if(ismulti != null){
            questionQueryWrapper.eq("ismulti", ismulti);
        }
        // 设置分页器
        Page<Question> page = new Page<>(p, limit);
        // 查询问题
        List<Question> questions = questionMapper.selectPage(page, questionQueryWrapper).getRecords();

        return getQuestionMultipleDataResult(questions, page);
    }

    /**
     * 批量导入题目到题库中
     *
     * @param file excel文件流
     * @param fileExt 文件各式
     * @param belongClass 所属班级
     * @return 状态
     * @throws IOException 可能出现文件解析错误等异常
     */
    @Override
    public BaseResult importQuestion(InputStream file, String fileExt, Integer belongClass) throws IOException {
        BaseResult result = new BaseResult();
        // 将问题集保存到列表中
        List<Question> questions = new ArrayList<>();
        if("xls".equals(fileExt)){
            // 得到工作簿
            Workbook workbook = new HSSFWorkbook(file);
            if (batch(belongClass, result, questions, workbook)) return result;
        } else if("xlsx".equals(fileExt)) {
            // 得到工作簿
            Workbook workbook = new XSSFWorkbook(file);
            if (batch(belongClass, result, questions, workbook)) return result;
        } else {
            result.setCode(1);
            result.setMessage("上传的文件格式不符合要求");
        }
        // 关闭文件流
        file.close();
        return result;
    }

    private boolean batch(Integer belongClass, BaseResult result, List<Question> questions, Workbook workbook) {
        // 得到工作表
        Sheet sheet = workbook.getSheet("题目集");
        // 得到行数
        int rows = sheet.getLastRowNum();
        for (int i = 1; i < rows; i++) {
            try {
                // 得到当前行
                Row row = sheet.getRow(i);
                // 取出表格中的数据，将其放入到实体类中
                Question question = new Question();
                // 问题名称
                question.setQuestionname(row.getCell(0).getStringCellValue());
                // 题型
                String type = row.getCell(1).getStringCellValue();
                // 是否为多选
                if ("是".equals(type)) {
                    question.setIsmulti(true);
                } else if ("否".equals(type)) {
                    question.setIsmulti(false);
                } else {
                    result.setCode(1);
                    result.setMessage("错误：第" + (i + 1) + "行第2列输入的值不符合要求");
                    return true;
                }
                // A选项
                Cell cell3 = row.getCell(2);
                cell3.setCellType(CellType.STRING);
                String option1 = cell3.getStringCellValue();
                question.setOption1(option1);
                // B选项
                Cell cell4 = row.getCell(3);
                cell4.setCellType(CellType.STRING);
                String option2 = cell3.getStringCellValue();
                question.setOption2(option2);
                // C选项
                Cell cell5 = row.getCell(4);
                if (cell5.getCellType() != CellType.BLANK) {
                    cell5.setCellType(CellType.STRING);
                    String option3 = cell3.getStringCellValue();
                    question.setOption3(option3);
                }
                // D选项
                Cell cell6 = row.getCell(5);
                if (cell6.getCellType() != CellType.BLANK) {
                    cell6.setCellType(CellType.STRING);
                    String option4 = cell6.getStringCellValue();
                    question.setOption4(option4);
                }
                // D选项
                Cell cell7 = row.getCell(6);
                if (cell7.getCellType() != CellType.BLANK) {
                    cell7.setCellType(CellType.STRING);
                    String option5 = cell7.getStringCellValue();
                    question.setOption5(option5);
                }
                // 正确选项
                String answer = row.getCell(7).getStringCellValue();
                question.setAnswer(answer);
                // 答案解析
                Cell cell8 = row.getCell(8);
                cell8.setCellType(CellType.STRING);
                String reason = cell8.getStringCellValue();
                question.setReason(reason);
                // 所属班级
                question.setBelongclass(belongClass);
                // 难度系数
                Cell cell9 = row.getCell(9);
                double level = cell9.getNumericCellValue();
                question.setLevel((int) level);
                questions.add(question);
            } catch (Exception e) {
                result.setCode(1);
                result.setMessage("批量导入失败，请检查表格中的内容是否输入合理");
                return false;
            }
        }
        boolean res = questionMapper.insertBatch(questions);
        result.setCode(res ? 0 : 1);
        result.setMessage(res ? "批量导入成功" : "批量导入失败");
        return false;
    }

    /**
     * 单独封装的返回结果
     *
     * @param list 查询到的数据
     * @param page 分页器
     * @return 当前页的班级信息或者所有的班级信息
     */
    private MultipleDataResult<Question> getQuestionMultipleDataResult(List<Question> list, Page<Question> page) {
        // 返回数据
        MultipleDataResult<Question> dataResult = new MultipleDataResult<>();
        // 设置数据
        dataResult.setData(list);
        dataResult.setCode(0);
        // 判断是否传入分页器参数
        if(page != null) {
            dataResult.setTotal((int) page.getTotal());
        }
        else {
            dataResult.setTotal(list.size());
        }
        return dataResult;
    }
}
