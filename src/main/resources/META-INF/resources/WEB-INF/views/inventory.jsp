<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-5">
        <a class="navbar-brand" href="#">Smart Inventory Management System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarText">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
              <a class="nav-link" href="index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="viewallinventory">Inventory</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="viewallproducts">Products</a>
              </li>
            
          </ul>
          <form class="form-inline d-flex" action = "searchproduct" method="post">
            <input class="form-control mr-sm-2 me-2 ms-2" type="text" name="name" placeholder="Enter Product Name" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>

	<div class="d-flex flex-column justify-content-center align-items-center">
	
	<h3 class="mb-5">Inventory Tracker</h3>
<div class="mb-4">
<form action="searchinventory">
<i style = "font-size : 20px" class="bi bi-search"></i> <span><input type="text" name = "name" placeholder="Enter name to Search"></span>
<input style = "font-size:16px" type="submit" value="Search" class="btn btn-outline-success">
</form>
</div>
		<table border="1" class="table w-75 table-striped">
			<thead class="table-danger">
				<th class="text-center"><a href="?pageNo=${currentPage}&sortField=product.name&sortOrder=${revSortOrder}">Product Name</a></th>
				<th class="text-center"><a href="?pageNo=${currentPage}&sortField=quantity&sortOrder=${revSortOrder}">Quantity</a></th>
				<th class="text-center"><a href="?pageNo=${currentPage}&sortField=threshold&sortOrder=${revSortOrder}">Threshold</a></th>
			</thead>
			<c:forEach items="${inventory}" var="i">
				<tr class="table-warning">

					<td class="text-center">${i.product.name}</td>
					<td class="text-center">${i.quantity}</td>
					<td class="text-center">${i.threshold}</td>
				</tr>
			</c:forEach>
			
		</table>
		<div class="d-inline float-end">

<c:if test="${currentPage > 0}">
    <a href="?pageNo=${currentPage - 1}&sortField=${sortField}&sortOrder=${sortOrder}">Previous</a>
</c:if>
<c:forEach var="i" begin="0" end="${totalPages - 1}">
    <c:choose>
        <c:when test="${currentPage == i}">
            <span>${i + 1}</span>
        </c:when>
        <c:otherwise>
            <a href="?pageNo=${i}&sortField=${sortField}&sortOrder=${sortOrder}">${i + 1}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${currentPage < totalPages - 1}">
    <a href="?pageNo=${currentPage + 1}&sortField=${sortField}&sortOrder=${sortOrder}">Next</a>
</c:if>
</div>
	</div>
</body>
</html>