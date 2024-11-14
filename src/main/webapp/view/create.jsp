
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/tt/create" method="post">
    <div class="form-group">
        <label for="tenantName">Tên người thuê:</label>
        <input type="text" class="form-control" id="tenantName" name="tenantName" required>
    </div>

    <div class="form-group">
        <label for="phoneNumber">Số điện thoại:</label>
        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
    </div>

    <div class="form-group">
        <label for="rentalStartDate">Ngày bắt đầu thuê:</label>
        <input type="date" class="form-control" id="rentalStartDate" name="rentalStartDate" required>
    </div>

    <div class="form-group">
        <label for="paymentMethod">Hình thức thanh toán:</label>
        <select class="form-control" id="paymentMethod" name="paymentMethodID" required>
            <c:if test="${not empty list}">
                <c:forEach var="v" items="${list}">
                    <option value="${v.paymentMethodID}">${v.paymentMethodName}</option>
                </c:forEach>
            </c:if>
        </select>
    </div>

    <div class="form-group">
        <label for="notes">Ghi chú:</label>
        <textarea class="form-control" id="notes" name="notes"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Thêm</button>
</form>
</body>
</html>
