<header class="p-3 text-white" style="background-color: whitesmoke">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start row" tyle="
             width: 1296px;
             height: 40px;
             ">
            <a href="home" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <img src="img/mu.jpg" alt="" style="width: 85px; height: 25px; margin-right: 50px">
            </a>


            <form class="col-md-8 col-lg-auto mb-3 mb-lg-0 me-lg-3 mr-4" action="home">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="search" class="form-control form-control-dark col-md-12" style="width: 500px; height: 40px; border: 1px solid black" placeholder="Search..."
                       aria-label="Search" name = "search" value="${requestScope.search}">
            </form>
            <div class="header__right col-md-4">
                <c:if test="${sessionScope.account != null}">
                    <div class="d-flex align-items-center">
                        <c:if test="${sessionScope.account.role == 1   }">
                            <a href="dashboard">
                                <button class="btn btn-info"style="margin-top: 10px; margin-left: 20px; margin-right: 30px;margin-bottom: 10px ; background-color: white; color:#58abff;">Admin</button>
                            </a>
                        </c:if>
                        
                        <div class="dropdown" style="margin-top: 10px; margin-left: 50px; margin-bottom: 13px;">
                            <button class="btn btn-secondary"style="background-color: white; color:#58abff;margin-bottom: 2px;  padding: 5px 30px" type="button" data-toggle="dropdown" aria-expanded="false">
                                <span>
                                    <img src="img/mu.jpg" style="width: 20px;height: 20px;object-fit: cover; margin-left: -20px">
                                </span>
                                <span>
                                    ${sessionScope.account.username}
                                </span>
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="change-information">Change Information</a>
                                <a class="dropdown-item" href="history-purchase">History Purchase</a>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <div class="d-flex">
                        <div class="register-button">
                            <a href="register" style="color: white; text-decoration: none">Sign up</a>
                        </div>
                        <div class="login-button">
                            <a href="login" style="color: white; text-decoration: none">Log in</a>
                        </div>
                    </div>
                </c:if>
                    <c:if test="${sessionScope.account != null}">
                    <div class="register-button">
                        <a href="UserProfile" style="color: white; text-decoration: none">Profile</a>
                    </div>
                    <div class="register-button">
                        <a  href="ChangePassword" style="color: white; text-decoration: none; ">Change Password</a>

                    </div>
                </c:if>
            </div>
        </div>
    </div>
</header>