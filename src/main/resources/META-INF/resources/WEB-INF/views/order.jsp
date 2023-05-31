<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-5">
		<a class="navbar-brand" href="#">Smart Inventory Management System</a>
		<div class="collapse navbar-collapse justify-content-end"
			id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link" href="index">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="viewallinventory">Inventory</a></li>
				<li class="nav-item"><a class="nav-link" href="viewallproducts">Products</a>
				</li>
				
			</ul>
			<form class="form-inline d-flex" action="searchproduct" method="post">
				<input class="form-control mr-sm-2 me-2 ms-2" type="text"
					name="name" placeholder="Enter Product Name" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div class="container" style="width: 30%; float: center">
		<h4>${success}</h4>
		<form action="registerOrder" method="post">
			<h3>Enter Customer Details:</h3>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="name"
					placeholder="Enter Your Name">
			</div>
			<div class="form-group">
				<label for="email">Email Id:</label> <input type="text"
					class="form-control" id="email" name="email"
					placeholder="Enter Your Email">
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					class="form-control" id="address" name="address"
					placeholder="Enter Your Address">
			</div>
			<h3>Enter Order Details:</h3>
			<div class="form-group">
				<label for="date">Date:</label> <input type="date"
					class="form-control" id="date" name="date"
					placeholder="Select Date">
			</div>
			<div class="form-group" id="product-list">
				<div class="form-group">
					<label for="productName">Product Name:</label> <select
						class="form-control" id="product" name="id[]">
						<option value="">Select Product</option>
						<c:forEach items="${products}" var="p">
							<option value="${p.id}">${p.name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="form-group">
					<label for="productQuantity">Product Quantity:</label> <input
						type="number" class="form-control" id="productQuantity"
						name="quantity[]" placeholder="Enter Quantity">
				</div>
			</div>
			<br> <input type="submit" class="btn btn-primary"
				value="Place Order">
			<button type="button" class="btn btn-primary" id="add-item-btn">Add
				Another Item</button>

		</form>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							var productCount = 0;

							$("#add-item-btn")
									.click(
											function() {
												productCount++;
												var productHtml = '<div class="product-list">'
														+ '<div class="form-group">'
														+ '<label for="productName">Product Name:</label>'
														+ '<select class="form-control" id="product" name="id[]">'
														+ '<option value="">Select Product</option>'
														+ '<c:forEach items="${products}" var="p">'
														+ '<option value="${p.id}">${p.name}</option>'
														+ '</c:forEach>'
														+ '</select>'
														+ '</div>'
														+ '<div class="form-group">'
														+ '<label for="productQuantity">Product Quantity:</label>'
														+ '<input type="number" class="form-control" id="productQuantity" name="quantity[]"placeholder="Enter Quantity">'
														+ '</div>' + '</div>';

												$("#product-list").append(
														productHtml);
											});
						});
	</script>
</body>
</html>