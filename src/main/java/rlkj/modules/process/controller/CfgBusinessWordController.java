package rlkj.modules.process.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rlkj.modules.process.entity.CfgBusinessWordEntity;
import rlkj.modules.process.service.CfgBusinessWordService;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;



/**
 * 业务关键词字典表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-07 19:28:30
 */
@RestController
@RequestMapping("process/cfgbusinessword")
public class CfgBusinessWordController {
    @Autowired
    private CfgBusinessWordService cfgBusinessWordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cfgBusinessWordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
			CfgBusinessWordEntity cfgBusinessWord = cfgBusinessWordService.selectById(id);

        return R.ok().put("cfgBusinessWord", cfgBusinessWord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CfgBusinessWordEntity cfgBusinessWord){
			cfgBusinessWordService.insert(cfgBusinessWord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CfgBusinessWordEntity cfgBusinessWord){
			cfgBusinessWordService.updateById(cfgBusinessWord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			cfgBusinessWordService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
