<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Người Thuê Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container mt-8">


    <h1 class="text-center mb-4">Danh sách Người Thuê Trọ</h1>
    <nav class="navbar navbar-light bg-light py-3">
        <div class="container-fluid">

            <form class="d-flex" action="/tt/find">
                <input  name="keyword" class="form-control me-3 shadow-sm" type="search" placeholder="Tìm kiếm..." aria-label="Search" style="max-width: 300px;">
                <button class="btn btn-success shadow-sm" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </nav>
    <!-- Nút Thêm Mới -->
    <div class="mb-3 text-end">
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addModal">Thêm mới</button>
    </div>

    <!-- Hiển thị tổng số người thuê -->
    <div class="alert alert-info">
        <strong>Tổng số người thuê:</strong> <c:out value="${data.size()}"/>
    </div>

    <!-- Chọn Xóa Nhiều -->
    <form method="post">
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="selectAll" onclick="selectAll()">
            <label class="form-check-label" for="selectAll">Chọn tất cả</label>
        </div>

        <!-- Bảng Danh sách Người thuê -->
        <table class="table table-striped table-bordered" id="tenantTable">
            <thead class="table-dark">
            <tr>
                <th><input type="checkbox" class="form-check-input" onclick="selectAll()" /></th>
                <th>STT</th>
                <th>Mã phòng trọ</th>
                <th>Tên người thuê trọ</th>
                <th>Số điện thoại</th>
                <th>Ngày bắt đầu thuê</th>
                <th>Hình thức thanh toán</th>
                <th>Ghi chú</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <!-- Lặp qua từng người thuê trọ -->
            <c:forEach items="${data}" var="o" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="roomIDs" value="${o.roomID}" class="form-check-input"></td>
                    <td><c:out value="${status.index + 1}"/></td> <!-- Hiển thị STT -->
                    <td><c:out value="${o.roomID}"/></td>
                    <td><c:out value="${o.tenantName}"/></td>
                    <td><c:out value="${o.phoneNumber}"/></td>
                    <td><c:out value="${o.rentalStartDate}"/></td>
                    <td><c:out value="${o.paymentMethodName}"/></td>
                    <td><c:out value="${o.notes}"/></td>
                    <td>
                        <!-- Nút xóa -->
                        <a class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có muốn xóa thông tin thuê trọ với mã phòng ' + '${o.roomID}' + ' hay không?')"
                           href="${pageContext.request.contextPath}/tt/delete?roomID=${o.roomID}">Xóa</a>

                        <!-- Nút sửa -->
                        <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/tt/update?roomID=${o.roomID}">Sửa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>


<!-- Modal Thêm Mới -->
<div class="modal fade" id="addModal" tabindex="1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Thêm Thông tin Người Thuê</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/tt/create" method="post" onsubmit="return validateAddForm()">
                    <div class="form-group mb-3">
                        <label for="tenantName">Tên người thuê:</label>
                        <input type="text" class="form-control" id="tenantName" name="tenantName" required oninput="validateTenantName()">
                        <div id="tenantNameError" class="invalid-feedback" style="display: none;">Không chứa kí tự số và kí tự đặc biệt, độ dài từ 5 đến 50 kí tự.</div>
                    </div>

                    <div class="form-group mb-3">
                        <label for="phoneNumber">Số điện thoại:</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required oninput="validatePhoneNumber()">
                        <div id="phoneNumberError" class="invalid-feedback" style="display: none;">Số điện thoại phải có 10 ký tự số.</div>
                    </div>

                    <div class="form-group mb-3">
                        <label for="rentalStartDate">Ngày bắt đầu thuê:</label>
                        <input type="date" class="form-control" id="rentalStartDate" name="rentalStartDate" required oninput="validateRentalStartDate()">
                        <div id="rentalStartDateError" class="invalid-feedback" style="display: none;">Ngày bắt đầu thuê không được là ngày quá khứ.</div>
                    </div>

                    <div class="form-group mb-3">
                        <label >Hình thức thanh toán:</label>
                        <select class="form-control" name="paymentMethodID" required>
                            <c:if test="${not empty list}">
                                <c:forEach var="v" items="${list}">
                                    <option value="${v.paymentMethodID}">${v.paymentMethodName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="form-group mb-3">
                        <label for="notes">Ghi chú:</label>
                        <textarea class="form-control" id="notes" name="notes" maxlength="200" oninput="validateNotes()"></textarea>
                        <div id="notesError" class="invalid-feedback" style="display: none;">Cho phép nhập không quá 200 kí tự.</div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Thêm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    // Real-time validation functions
    function validateTenantName() {
        var tenantName = document.getElementById('tenantName').value;
        var tenantNameRegex = /^[a-zA-Z\s]{5,50}$/;
        var errorMessage = document.getElementById('tenantNameError');
        var tenantNameInput = document.getElementById('tenantName');
        if (!tenantNameRegex.test(tenantName)) {
            tenantNameInput.classList.add('is-invalid');
            errorMessage.style.display = 'block';
        } else {
            tenantNameInput.classList.remove('is-invalid');
            errorMessage.style.display = 'none';
        }
    }

    function validatePhoneNumber() {
        var phoneNumber = document.getElementById('phoneNumber').value;
        var phoneRegex = /^[0-9]{10}$/;
        var errorMessage = document.getElementById('phoneNumberError');
        var phoneNumberInput = document.getElementById('phoneNumber');
        if (!phoneRegex.test(phoneNumber)) {
            phoneNumberInput.classList.add('is-invalid');
            errorMessage.style.display = 'block';
        } else {
            phoneNumberInput.classList.remove('is-invalid');
            errorMessage.style.display = 'none';
        }
    }

    function validateRentalStartDate() {
        var rentalStartDate = document.getElementById('rentalStartDate').value;
        var currentDate = new Date().toISOString().split('T')[0];  // Get current date in YYYY-MM-DD format
        var errorMessage = document.getElementById('rentalStartDateError');
        var rentalStartDateInput = document.getElementById('rentalStartDate');
        if (rentalStartDate < currentDate) {
            rentalStartDateInput.classList.add('is-invalid');
            errorMessage.style.display = 'block';
        } else {
            rentalStartDateInput.classList.remove('is-invalid');
            errorMessage.style.display = 'none';
        }
    }

    function validateNotes() {
        var notes = document.getElementById('notes').value;
        var errorMessage = document.getElementById('notesError');
        var notesInput = document.getElementById('notes');
        if (notes.length > 200) {
            notesInput.classList.add('is-invalid');
            errorMessage.style.display = 'block';
        } else {
            notesInput.classList.remove('is-invalid');
            errorMessage.style.display = 'none';
        }
    }

    // Validate form before submission
    function validateAddForm() {
        validateTenantName();
        validatePhoneNumber();
        validateRentalStartDate();
        validateNotes();

        // If any error message is displayed, prevent form submission
        var errorMessages = document.querySelectorAll('.invalid-feedback');
        for (var errorMessage of errorMessages) {
            if (errorMessage.style.display === 'block') {
                return false;  // Prevent form submission
            }
        }

        return true;  // Submit the form
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>




