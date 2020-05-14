package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户中心我的订单", tags = "用户中心我的订单")
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @Autowired
    private MyOrdersService myOrdersService;

    @PostMapping("/query")
    @ApiOperation(value = "查询订单评论", notes = "查询订单评论", httpMethod = "POST")
    public IMOOCJSONResult comments(@ApiParam(name = "userId", value = "用户id", required = true)
                                    @RequestParam String userId,
                                    @ApiParam(name = "orderStatus", value = "订单状态", required = false)
                                    @RequestParam Integer orderStatus,
                                    @ApiParam(name = "page", value = "查询下一页的地址页", required = false)
                                    @RequestParam Integer page,
                                    @ApiParam(name = "pageSize", value = "分页的每一页显示的记录数", required = false)
                                    @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return IMOOCJSONResult.ok(grid);
    }
}
