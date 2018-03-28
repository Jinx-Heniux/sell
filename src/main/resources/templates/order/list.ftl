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
                                    <td>${item.orderStatus}</td>
                                    <td>${item.payStatus}</td>
                                    <td>${item.createTime}</td>
                                    <td>详情</td>
                                    <td>取消</td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>