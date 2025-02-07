<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

<div class="container mt-4">
    <h3 class="pb-3 pt-3 text-center">Reports Application</h3>

    <div class="card p-4 shadow">
      <form:form action="search" modelAttribute="searchDto" method="POST">

    <div class="row mb-3">
        <div class="col-md-4">
            <label class="form-label">Plan Name:</label>
            <form:select path="planName" class="form-select">
                <form:option value="">- Select -</form:option>
                <form:options items="${names}" />
            </form:select>
        </div>

        <div class="col-md-4">
            <label class="form-label">Plan Status:</label>
            <form:select path="planStatus" class="form-select">
                <form:option value="">- Select -</form:option>
                <form:options items="${status}" />
            </form:select>
        </div>

        <div class="col-md-4">
            <label class="form-label">Gender:</label>
            <form:select path="gender" class="form-select">
                <form:option value="">- Select -</form:option>
                <form:option value="Male">Male</form:option>
                <form:option value="Female">Female</form:option>
            </form:select>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-md-6">
            <label class="form-label">Start Date:</label>
            <form:input path="startDate" type="date" class="form-control"/>
        </div>
        <div class="col-md-6">
            <label class="form-label">End Date:</label>
            <form:input path="endDate" type="date" class="form-control"/>
        </div>
    </div>

    <!-- Buttons section with proper alignment -->
    <div class="d-flex justify-content-center gap-3">
        <a href="/" class="btn btn-secondary">Reset</a>
        <input type="submit" value="Search" class="btn btn-primary"/>
    </div>
</form:form>

    </div>

  <hr>
  <table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>S.No</th>
            <th>Holder Name</th>
            <th>Gender</th>
            <th>Plan Name</th>
            <th>Plan Status</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${plans}" var="plan" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>${plan.citizenName}</td>
                <td>${plan.gender}</td>
                <td>${plan.planName}</td>
                <td>${plan.planStatus}</td>
                <td>${plan.planStartDate}</td>
                <td>${plan.planEndDate}</td>
                <td>${plan.benefitAmount}</td>
            </tr>
        </c:forEach>
        <tr>
        <c:if test="${empty plans}">
       <td colspan="8" style="text-align:center;"> No Records Found</td>

        </c:if>
        </tr>
    </tbody>
</table>

    <div class="text-center mt-3">
        <h5>Export:</h5>
        <a href="#" class="btn btn-success me-2">Excel</a>
        <a href="#" class="btn btn-danger">PDF</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

</body>
</html>