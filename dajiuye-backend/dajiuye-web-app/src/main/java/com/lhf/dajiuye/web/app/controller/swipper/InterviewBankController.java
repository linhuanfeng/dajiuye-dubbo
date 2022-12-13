package com.lhf.dajiuye.web.app.controller.swipper;

import com.hu.health.common.utils.PageUtils;
import com.hu.health.common.utils.R;
import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.bean.swipper.InterviewBank;
import com.lhf.dajiuye.api.service.swipper.InterviewBankService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/swipper/interview")
@Slf4j
public class InterviewBankController {

    @DubboReference(interfaceClass = InterviewBankService.class,version = "1.0.0",check = false,retries = 0,timeout =10000 )
    private InterviewBankService interviewBankService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("community:area:list")
    public Object list(@RequestParam Map<String, Object> params){
        PageUtils page = interviewBankService.queryPage(params);
        List<?> list = page.getList();
        List<InterviewBank> collect = list.stream().map(o -> (InterviewBank) o).collect(Collectors.toList());
        return new CommonResult<InterviewBank>(collect,new Meta("获取成功",200));
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("community:area:info")
    public R info(@PathVariable("id") Long id){
        InterviewBank interviewBank = interviewBankService.getById(id);

        return R.ok().put("interviewBank", interviewBank);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("community:area:save")
    public R save(@RequestBody InterviewBank interviewBank){
        interviewBankService.save(interviewBank);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("community:area:update")
    public R update(@RequestBody InterviewBank interviewBank){
        interviewBankService.updateById(interviewBank);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("community:area:delete")
    public R delete(@RequestBody Long[] ids){
        interviewBankService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
