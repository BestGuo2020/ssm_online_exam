package top.bestguo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.bestguo.entity.Question;
import top.bestguo.render.BaseResult;
import top.bestguo.render.MultipleDataResult;
import top.bestguo.service.TikuService;
import top.bestguo.vo.QuestionCondition;

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
}
