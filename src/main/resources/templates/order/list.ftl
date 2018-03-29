<html>
    <header>
        <meta charset="utf-8">
        <title>卖家后端管理系统</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </header>
    <body>
        <#-- 主要内容-->
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>订单编号</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list orderDtoPage.content as item>
                                <tr>
                                    <td>${item.orderId}</td>
                                    <td>${item.buyerName}</td>
                                    <td>${item.buyerPhone}</td>
                                    <td>${item.buyerAddress}</td>
                                    <td>${item.orderAmount}</td>
                                    <td>${item.getOrderStatusEnum().message}</td>
                                    <td>${item.getPayStatusEnum().message}</td>
                                    <td>${item.createTime}</td>
                                    <td><a href="/sell/seller/order/detail?orderId=${item.orderId}">详情</a></td>
                                    <td>
                                        <#if item.getOrderStatusEnum().message == "新订单">
                                            <a href="/sell/seller/order/cancel?orderId=${item.orderId}">取消</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..orderDtoPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte orderDtoPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>