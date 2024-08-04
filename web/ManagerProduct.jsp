<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8" />
    <title>Responsive Admin Dashboard | Cyborg</title>
    <link rel="stylesheet" href="assets/style.css" />
    <!-- Boxicons CDN Link -->
    <link href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        /* Style for modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
            padding-top: 60px;
        }
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 30%;
            border-radius: 10px;
            position: relative;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            position: absolute;
            top: 10px;
            right: 20px;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .modal-body {
            text-align: center;
        }
        .modal-body img {
            width: 100px;
            height: 100px;
            display: block;
            margin: 0 auto 20px auto;
        }
        .modal-body button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin-top: 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .modal-body button:hover {
            background-color: #45a049;
        }
    </style>
    <style>
    .details {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    .details th, .details td {
        padding: 8px;
        text-align: left;
        border: 1px solid #ddd;
    }
    .details th {
        background-color: #f2f2f2;
    }
    .details tr:hover {
        background-color: #f5f5f5;
    }
</style>
<style>
        /* Save button styles */
        .save2-button {
            display: inline-block;
            padding: 5px 10px; /* Adjusted padding for the new size */
            font-size: 1rem;
            font-weight: 600;
            color: #fff;
            background-color: #001f5b; /* Dark blue color */
            border: none;
            border-radius: 8px; /* Rounded corners */
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            width: 60px; /* Fixed width */
            height: 31px; /* Fixed height */
        }

        .save2-button:hover {
            background-color: #00163e; /* Slightly darker shade on hover */
        }

        .save2-button:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(0, 31, 91, 0.25); /* Blue shadow on focus */
        }

        .save2-button:active {
            background-color: #000b2d; /* Even darker shade on active */
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2); /* Inset shadow on active */
        }

        /* Add margin to separate the table and button */
        .button {
            margin-top: 10px;
        }

    </style>
    <style>
    /* Save button styles */
    .add-button {
        display: inline-block;
        padding: 5px 10px; /* Adjusted padding for the new size */
        font-size: 1rem;
        font-weight: 600;
        color: #fff;
        background-color: #006400; /* Dark green color */
        border: none;
        border-radius: 8px; /* Rounded corners */
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease, box-shadow 0.3s ease;
        width: 60px; /* Fixed width */
        height: 31px; /* Fixed height */
    }

    .add-button:hover {
        background-color: #004d00; /* Slightly darker shade on hover */
    }

    .add-button:focus {
        outline: none;
        box-shadow: 0 0 0 3px rgba(0, 100, 0, 0.25); /* Green shadow on focus */
    }

    .add-button:active {
        background-color: #003300; /* Even darker shade on active */
        box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2); /* Inset shadow on active */
    }

    /* Add margin to separate the table and button */
    .button {
        margin-top: 10px;
    }
</style>
     <style>
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
            color: black;
        }
        .pagination a.current {
            text-decoration: underline;
        }
    </style>
    <style>
/* Modal container */
.modal1 {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
    padding-top: 60px;
}

/* Modal content */
.modal1-content {
    background-color: #fefefe;
    margin: 5% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 30%;
    border-radius: 10px;
    position: relative;
}

/* Close button */
.close1 {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    position: absolute;
    top: 10px;
    right: 20px;
}

.close1:hover,
.close1:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

/* Modal body */
.modal1-body {
    text-align: center;
}

.modal1-body form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.modal1-body form label {
    margin-top: 10px;
    text-align: left;
    width: 100%;
}

.modal1-body form input,
.modal1-body form select {
    margin-top: 5px;
    padding: 5px;
    width: 100%;
    box-sizing: border-box;
}

.modal1-body form button {
    margin-top: 20px;
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
}

.modal1-body form button:hover {
    background-color: #45a049;
}
        </style>
        <style>
    /* Save button styles */
    .NewProduct-button {
        display: inline-block;
        padding: 5px 10px; /* Adjusted padding for the new size */
        font-size: 1rem;
        font-weight: 600;
        color: #fff;
        background-color: #006400; /* Dark green color */
        border: none;
        border-radius: 8px; /* Rounded corners */
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease, box-shadow 0.3s ease;
        /* Width and height will adjust based on text content */
    }

    .NewProduct-button:hover {
        background-color: #004d00; /* Slightly darker shade on hover */
    }

    .NewProduct-button:focus {
        outline: none;
        box-shadow: 0 0 0 3px rgba(0, 100, 0, 0.25); /* Green shadow on focus */
    }

    .NewProduct-button:active {
        background-color: #003300; /* Even darker shade on active */
        box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2); /* Inset shadow on active */
    }

    /* Add margin to separate the table and button */
    .button {
        margin-top: 10px;
    }
</style>
<style>
    /* Save button styles */
    .NewCategory-button {
        display: inline-block;
        padding: 5px 10px; /* Adjusted padding for the new size */
        font-size: 1rem;
        font-weight: 600;
        color: #fff;
        background-color: #006400; /* Dark green color */
        border: none;
        border-radius: 8px; /* Rounded corners */
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease, box-shadow 0.3s ease;
        /* Width and height will adjust based on text content */
    }

    .NewCategory-button:hover {
        background-color: #004d00; /* Slightly darker shade on hover */
    }

    .NewCategory-button:focus {
        outline: none;
        box-shadow: 0 0 0 3px rgba(0, 100, 0, 0.25); /* Green shadow on focus */
    }

    .NewCategory-button:active {
        background-color: #003300; /* Even darker shade on active */
        box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.2); /* Inset shadow on active */
    }

    /* Add margin to separate the table and button */
    .button {
        margin-top: 10px;
    }
</style>
</head>
<body>
    <div class="sidebar">
        <div class="logo-details">
            <i class="bx bxl-c-plus-plus"></i>
            <span class="logo_name">Cyborg</span>
        </div>
        <ul class="nav-links">
            <!-- Các liên kết khác -->
            <li>
                <a href="Chung">
                    <i class="bx bx-grid-alt"></i>
                    <span class="links_name">Chung</span>
                </a>
            </li>
            <li>
                <a href="adminProduct" class="active">
                    <i class="bx bx-box"></i>
                    <span class="links_name">Sản phẩm</span>
                </a>
            </li>

            <li>
                <a href="controllerAccount">
                    <i class="bx bx-user"></i>
                    <span class="links_name">View List Account</span>
                </a>
            </li>
            <li>
                <a href="FeedBackAdmin" >
                    <i class="bx bx-message"></i>
                    <span class="links_name">Báo Cáo Sự Cố</span>
                </a>
            </li>               
            <li>
                    <a href="home">
                        <i class="bx bx-list-ul"></i>
                        <span class="links_name">Home</span>
                    </a>
                </li>
            <li class="log_out">
                <a href="logout">
                    <i class="bx bx-log-out"></i>
                    <span class="logout" >Log out</span>
                </a>
            </li>
        </ul>
    </div>
    <section class="home-section">
        
        <nav>
            <div class="sidebar-button">
                <i class="bx bx-menu sidebarBtn"></i>
                <span class="dashboard">Dashboard</span>
            </div>
            <div class="search-box">
                <form action="adminProduct" method="get">
                    <input type="text" name="NameSearch" placeholder="Search..." style="height: 50px;" />
                    <button type="submit"><i class="bx bx-search"></i></button>
                </form>
            </div>
            <div class="profile-details">
                <img src="${account.getImg()}" alt="" />
                <span class="admin_name">${account.getName()}</span>
                <i class="bx bx-chevron-down"></i>
            </div>
        </nav>

        <!-- Table to display the data -->
        <div class="home-content">
            <div class="overview-boxes">
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Tổng số đơn hàng</div>
                                <div class="number">
         <div class="number">${totalorder} Đơn</div>
    </div>
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bx-cart-alt cart"></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Tổng số thẻ đã bán</div>
                            <div class="number">${totalquality} thẻ</div>
                            
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bxs-cart-add cart two"></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Tổng doanh thu</div>
                            <div class="number">${totalprice} K</div>
                            <div class="indicator">
                                <i class="bx bx-up-arrow-alt"></i>
                                <span class="text">Up from yesterday</span>
                            </div>
                        </div>
                        <i class="bx bx-cart cart three"></i>
                    </div> 
                </div>
            <div class="sales-boxes">
                <div class="recent-sales box">
                    
                    <div class="title">Danh sách sản phẩm</div>                   
    <button type="button" class="NewProduct-button" id="addNewProductBtn">Add New Product</button>


                    <div class="sales-details">
                       
                        <c:if test="${not empty listP}">
                            <form action="adminProduct" method="post">
                                <table  class="details">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Quality</th>
                                            <th>CateId</th>
                                            <th>Date created</th>
                                            <th>Status</th>
                                            <th>Add Card</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${listP}">
                                            <tr>
                                                <td>${product.id}</td>
                                                 <td>
                                                     <input type="text" name="name_${product.id}" value="${product.name}" style="width: 140px;" />
                                                 </td>
                                                 <td>
                                                      <input type="text" name="price_${product.id}" value="${product.price} VNĐ" style="width: 100px;" />
                                                   </td>
                                                <td>${product.quanlity}</td>
                                                
                                                <td>${product.cateID}</td>
                                                <td>${product.createdAt}</td>
                                                <td>
                                                    <!-- Input hidden để gửi giá trị false -->
                                                    <!-- Checkbox để gửi giá trị true nếu được chọn -->
                                                    <input type="checkbox" name="status_${product.id}" value="true" <c:if test="${product.isDelete}">checked="checked"</c:if>/>
                                                </td>
                                                <td>
                                                    <button type="button" class="add-button" data-id="${product.id}" data-img="img/${product.img}" data-name="${product.name}" > Add </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                    <div class="pagination">
    <c:if test="${maxPage > 1}">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}&NameSearch=${NameSearch}">&lt;</a>
        </c:if>
        
        <c:choose>
            <c:when test="${maxPage > 4}">
                <c:forEach var="i" begin="${currentPage - 1}" end="${currentPage + 1}">
                    <c:if test="${i > 0 && i <= maxPage}">
                        <a href="?page=${i}&NameSearch=${NameSearch}" class="${currentPage == i ? 'current' : ''}">${i}</a>
                    </c:if>
                </c:forEach>
                <c:if test="${currentPage + 2 <= maxPage}">
                    <span>...</span>
                </c:if>
                <a href="?page=${maxPage}&NameSearch=${NameSearch}" class="${currentPage == maxPage ? 'current' : ''}">${maxPage}</a>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="1" end="${maxPage}">
                    <a href="?page=${i}&NameSearch=${NameSearch}" class="${currentPage == i ? 'current' : ''}">${i}</a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        
        <c:if test="${currentPage < maxPage}">
            <a href="?page=${currentPage + 1}&NameSearch=${NameSearch}">&gt;</a>
        </c:if>
    </c:if>
</div>

                                <div class="button">
                                    <button type="submit" class="save2-button">Save</button> 
                                    
                                </div>
                            </form>

                        </c:if>
                        <c:if test="${empty listP}">
                            <p>No users found.</p>
                        </c:if>
                            
                    </div>
                                    
                </div>
                 <div class="top-sales box">
                        <div class="title">Các nhà cung cấp </div>
                                            <form action="adminCategory" method="post">                                      
                                     <button type="button" class="NewCategory-button" id="addNewCategoryBtn">Add New Category</button>                                                        
                                    </form>
                        <ul class="top-sales-details">
                                                    <c:if test="${not empty listC}">
                            <form action="adminCategory" method="post">
                                <table  class="details">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>                                 
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="category" items="${listC}">
                                            <tr>
                                                <td>${category.id}</td>
                                                <td>
                                                    <input type="text" name="namecate_${category.id}" value="${category.name}" style="width: 140px;" />
                                                </td>
                                                <td>
                                                    <!-- Input hidden để gửi giá trị false -->
                                                    <!-- Checkbox để gửi giá trị true nếu được chọn -->
                                                    <input type="checkbox" name="status1_${category.id}" value="true" <c:if test="${category.isDelete}">checked="checked"</c:if>/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                   
                                <div class="button">
                                    <button type="submit" class="save2-button">Save</button> 
                                    
                                </div>
                            </form>

                        </c:if>
                        <c:if test="${empty listC}">
                            <p>No users found.</p>
                        </c:if>
                        </ul>
                    </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <div class="modal-body">
            <img id="modal-img" src="" alt="IMG">
            <p id="modal-name"></p>
            <form id="uploadForm" action="uploadExcel" method="post" enctype="multipart/form-data">
    <input type="hidden" name="Name_ID" id="Name_ID" value="">
    <input type="file" name="file" id="file" accept=".xls,.xlsx" required>
    <button type="submit">Add File Excel</button>
</form>
        </div>
    </div>
</div>
<div id="addProductModal" class="modal1">
    <div class="modal1-content">
        <span class="close1">&times;</span>
        <h2>Add New Product</h2>
        <div class="modal1-body">
            <form id="addProductForm" action="addProduct" method="get" enctype="multipart/form-data">
                <label for="productCategory">Select Category:</label>
                <select name="productCategory" id="productCategory" required>
                    <!-- Add options dynamically based on available categories -->
                    <c:forEach var="category" items="${listC}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>

                <label for="productName">Enter Name:</label>
                <input type="text" name="productName" id="productName" required>

                <label for="productPrice">Enter Price:</label>
                <input type="number" name="productPrice" id="productPrice" required>

                <label for="productImage">Choose Img:</label>
                <input type="file" name="productImage" id="productImage" required>

                <button type="submit">Add</button>
            </form>
        </div>
    </div>
</div>
        <div id="addCategoryModal" class="modal1">
    <div class="modal1-content">
        <span class="close1">&times;</span>
        <h2>Add New Category</h2>
        <div class="modal1-body">
            <form id="addCategoryForm" action="addCategory" method="get" enctype="multipart/form-data">

                <label for="categoryName">Enter Name:</label>
                <input type="text" name="categoryName" id="categoryName" required>

                <button type="submit">Add</button>
            </form>
        </div>
    </div>
</div>
    </section>

    <script>
       let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".sidebarBtn");
sidebarBtn.onclick = function () {
    sidebar.classList.toggle("active");
    if (sidebar.classList.contains("active")) {
        sidebarBtn.classList.replace("bx-menu", "bx-menu-alt-right");
    } else {
        sidebarBtn.classList.replace("bx-menu-alt-right", "bx-menu");
    }
};

// Get modal element
var modal = document.getElementById("myModal");

// Get button that opens the modal
var addButtons = document.querySelectorAll(".add-button");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
addButtons.forEach(function(btn) {
    btn.onclick = function() {
        var imgSrc = this.getAttribute("data-img");
        var name = this.getAttribute("data-name");
        var id = this.getAttribute("data-id");

        document.getElementById("modal-img").src = imgSrc;
        document.getElementById("modal-name").innerText = "Name: " + name;
        document.getElementById("Name_ID").value = id; // Set Name_ID value

        modal.style.display = "block";
    };
});

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

    </script>
    <script>
// Get modal element for adding new product
var addProductModal = document.getElementById("addProductModal");

// Get button that opens the add product modal
var addNewProductBtn = document.getElementById("addNewProductBtn");

// Get the <span> element that closes the add product modal
var spanCloseProduct = addProductModal.getElementsByClassName("close1")[0];

// When the user clicks the button, open the modal
addNewProductBtn.onclick = function () {
    addProductModal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
spanCloseProduct.onclick = function () {
    addProductModal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == addProductModal) {
        addProductModal.style.display = "none";
    }
};

// Get the form element
var addProductForm = document.getElementById("addProductForm");

// Add an event listener to the form to validate before submission
addProductForm.addEventListener("submit", function (event) {
    // Get the product price input value
    var productPrice = document.getElementById("productPrice").value;
    
    // Check if the product price is greater than 0
    if (productPrice <= 0) {
        // If not, show an alert and prevent form submission
        alert("Product price must be greater than 0.");
        event.preventDefault();
    }
});
window.onclick = function (event) {
    if (event.target == addProductModal) {
        addProductModal.style.display = "none";
    }
};
    </script>
     <script>
// Get modal element for adding new product
var addCategoryModal = document.getElementById("addCategoryModal");

// Get button that opens the add product modal
var addNewCategoryBtn = document.getElementById("addNewCategoryBtn");

// Get the <span> element that closes the add product modal
var spanCloseProduct = addCategoryModal.getElementsByClassName("close1")[0];

// When the user clicks the button, open the modal
addNewCategoryBtn.onclick = function () {
    addCategoryModal.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
spanCloseProduct.onclick = function () {
    addCategoryModal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == addCategoryModal) {
        addCategoryModal.style.display = "none";
    }
};

// Get the form element
var addCategoryForm = document.getElementById("addCategoryForm");

// Add an event listener to the form to validate before submission
addCategoryForm.addEventListener("submit", function (event) {
    // Get the product price input value

});
window.onclick = function (event) {
    if (event.target == addCategoryModal) {
        addCategoryModal.style.display = "none";
    }
};
    </script>
</body>
</html>
