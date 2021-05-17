package top.bestguo.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.bestguo.entity.Question;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.TikuService;
import top.bestguo.vo.QuestionCondition;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/tiku")
public class TikuController {

    @Autowired
    private TikuService tikuService;

    /**
     * 题库管理
     *
     * @param question 问题实体类
     * @param select 功能选项：1-添加，2-修改，3-删除
     * @return 返回状态
     */
    @RequestMapping(value = "/manageQuestion/{select}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult manageQuestion(Question question, @PathVariable Integer select) {
        // 添加
        if (select == 1) {
            return tikuService.addQuestion(question);
        }
        // 修改
        if(select == 2) {
            return tikuService.modifyQuestion(question);
        }
        // 删除
        if(select == 3) {
            return tikuService.deleteQuestion(question.getId());
        }
        // 没有该选项
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage("没有这个功能");
        return result;
    }

    /**
     * 查询题库中的题目
     *
     * @param question 问题实体类
     * @param p 当前页数
     * @param limit 当前页面显示的数据个数
     * @return 查询状态和实体类
     */
    @RequestMapping(value = "/findQuestion", method = RequestMethod.POST)
    @ResponseBody
    public MultipleDataResult<Question> findAllQuestion(QuestionCondition question,
                                                        @RequestParam("page") Integer p,
                                                        @RequestParam("limit") Integer limit) {
        return tikuService.findAllQuestion(question, p, limit);
    }

    @RequestMapping(value = "/uploadQuestionSet", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult importQuestion(MultipartFile file, @RequestParam("belongclass") Integer belongclass) {
        BaseResult result = new BaseResult();
        if(belongclass == null) {
            result.setMessage("请选择要上传到的班级");
            result.setCode(1);
            return result;
        }
        // 得到文件流
        InputStream inputStream;
        // 获取文件后缀名
        String fileExt = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        try {
            // 获取文件流
            inputStream = file.getInputStream();
            return tikuService.importQuestion(inputStream, fileExt, belongclass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 未知异常信息
        result.setMessage("出现未知异常");
        result.setCode(1);
        return result;
    }

    /**
     * 删除多个题目
     *
     * @param ids 多个题目
     * @return
     */
    @RequestMapping(value = "/deleteQuestionMore", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteQuestionMore(Integer[] ids) {
        return tikuService.deleteQuestionMore(ids);
    }
}
