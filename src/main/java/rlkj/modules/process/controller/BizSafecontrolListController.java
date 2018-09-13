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

import rlkj.modules.process.entity.BizSafecontrolListEntity;
import rlkj.modules.process.service.BizSafecontrolListService;
import rlkj.common.utils.PageUtils;
import rlkj.common.utils.R;



/**
 * 安全措施清单表
 *
 * @author wanx
 * @email wanxiang@rarelong.com
 * @date 2018-09-08 18:03:36
 */
@RestController
@RequestMapping("process/bizsafecontrollist")
public class BizSafecontrolListController {
    @Autowired
    private BizSafecontrolListService bizSafecontrolListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("process:bizsafecontrollist:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bizSafecontrolListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("process:bizsafecontrollist:info")
    public R info(@PathVariable("id") Integer id){
			BizSafecontrolListEntity bizSafecontrolList = bizSafecontrolListService.selectById(id);

        return R.ok().put("bizSafecontrolList", bizSafecontrolList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("process:bizsafecontrollist:save")
    public R save(@RequestBody BizSafecontrolListEntity bizSafecontrolList){
			bizSafecontrolListService.insert(bizSafecontrolList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("process:bizsafecontrollist:update")
    public R update(@RequestBody BizSafecontrolListEntity bizSafecontrolList){
			bizSafecontrolListService.updateById(bizSafecontrolList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("process:bizsafecontrollist:delete")
    public R delete(@RequestBody Integer[] ids){
			bizSafecontrolListService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
