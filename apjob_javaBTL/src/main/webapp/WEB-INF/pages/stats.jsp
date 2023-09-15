<%-- 
    Document   : stats
    Created on : Sep 15, 2023, 10:38:35 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<br>
<h1 class="text-center text-success mt-1">Thống kê báo cáo</h1>

<c:url value="/stats" var="action" />
<form:form method="post" action="${action}">
    <div class="row">
        <div class="col-md-5 col-xs-12">
            <div class="form-floating mb-3 mt-3">
                <input type="date" class="form-control" placeholder="Ngày tháng năm sinh" path="toDate" id="toDate" name="toDate" />
                <label for="toDate">Ngày tháng năm bắt đầu</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <input type="date" class="form-control" placeholder="Ngày tháng năm sinh" path="fromDate" id="fromDate" name="fromDate" />
                <label for="fromDate">Ngày tháng năm kết thúc</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <div>
                    <label for="quarter">Nhập quý của năm</label>
                    <select path="quarter" class="form-select" name="quarter" id="quarter">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div>

                <div>
                    <input type="number" class="form-control" placeholder="Nhập năm" path="year" id="year" name="year" />
                </div>
            </div>
            <div class="form-floating mb-3 mt-3">
                <button type="submit" id="submitButton" class="btn btn-primary">Lọc dữ liệu</button>
                <c:url value="/stats" var="action" />
                <button type="submit" id="submitButton" class="btn btn-success">Dữ liệu tổng</button>
            </div>

        </div>

        <div class="col-md-5 col-xs-12">
            <canvas id="statsChartId"></canvas>
        </div>
    </div>

</form:form>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let colors = [];
    let borderColors = [];
    let color_1, color_2, color_3;
    // Lấy tham chiếu đến canvas
    var ctx = document.getElementById('statsChartId').getContext('2d');

    // Lấy dữ liệu từ request (sử dụng JSTL)
    var chartData = ${stats};

    // Tạo mảng labels và data từ dữ liệu truy vấn
    let labels = [];
    let dataValues = [];

    for (var i = 0; i < chartData.length; i++) {
        labels.push(chartData[i].tagName); // Label từ truy vấn CriteriaBuilder
        dataValues.push(chartData[i].linkCVCount); // Dữ liệu từ truy vấn CriteriaBuilder

    }

    // Tạo màu ngẫu nhiên cho biểu đồ (tùy chỉnh màu sắc ở đây nếu cần)
    for (var i = 0; i < chartData.length; i++) {
        color_1 = parseInt(Math.random() * 255);
        color_2 = parseInt(Math.random() * 255);
        color_3 = parseInt(Math.random() * 255);
        colors.push(`rgba(` + color_1 + `,` + color_2 + `,` + color_3 + `, 0.2)`);
        borderColors.push(`rgba(` + color_1 + `,` + color_2 + `,` + color_3 + `, 1)`);
    }

    // Dữ liệu cho biểu đồ
    var data = {
        labels: labels,
        datasets: [{
                label: 'Số lượng CV nộp theo ngành',
                data: dataValues,
                backgroundColor: colors,
                borderColor: borderColors,
                borderWidth: 1
            }]
    };

    // Tạo biểu đồ cột
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
