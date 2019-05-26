<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parts manager</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2>Part Manager</h2>
			<hr />
            <!-- Add part button -->
			<input type="button" value="Add part"
				onclick="window.location.href='showForm'; return false;"
				class="btn btn-primary" />

			<br/><br/>
			<!-- Search -->
            <div class="panel panel-info">
                <div class="panel-body">
                    <!-- Search -->
                    <form cssClass="form-horizontal" method="GET">
						<div class="form-group">
						    <c:if test="${search == null}">
								<input type="search" name="search" placeholder="Search..." class="form-control"/>
                            </c:if>
						    <c:if test="${search != null}">
								<input type="search" name="search" value="${search}" class="form-control"/>
                            </c:if>
						</div>
				    </form>
                    <!-- end Search -->

                    <!-- Filter -->

                       <select id="cd-dropdown" class="form-control" onchange="top.location=this.value" name="filterDropDown">
                        <c:forEach var="tempFilter" items="${filterList}">
                            <c:url value="/list" var="filterURL">
                                <c:if test="${search != null}">
                                       <c:param name="search" value="${search}"/>
                                </c:if>
                                <c:param name="filter" value="${tempFilter}"/>
                                <c:if test="${param.sort != null}">
                                       <c:param name="sort" value="${param.sort}"/>
                                </c:if>
                            </c:url>
                            <option ${tempFilter.name() == param.filter ? 'selected' : ''} value="${filterURL}" >Filter: ${tempFilter.name}</option>
                        </c:forEach>
                       </select>
                    <!-- end Filter -->
                    <!-- Sort  -->

                       <select id="cd-dropdown" class="form-control" onchange="top.location=this.value" name="sortDropDown">
                        <c:forEach var="tempSort" items="${sortList}">
                            <c:url value="/list" var="sortURL">
                                <c:if test="${search != null}">
                                       <c:param name="search" value="${search}"/>
                                </c:if>
                                <c:if test="${param.filter != null}">
                                       <c:param name="filter" value="${param.filter}"/>
                                </c:if>
                                <c:param name="sort" value="${tempSort}"/>
                            </c:url>
                            <option ${tempSort.name() == param.sort ? 'selected' : ''} value="${sortURL}" >Sort: ${tempSort.name}</option>
                        </c:forEach>
                       </select>
                    <!-- end Sort -->

				</div>
			</div>
    <br/><br/>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Parts List</div>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered">
						<tr>
							<th>Part name</th>
							<th>Necessary</th>
							<th>Count in stock</th>
							<th>Action</th>
						</tr>

						<!-- loop over and print our customers -->
						<c:forEach var="tempPart" items="${parts}">

							<!-- construct an "update" link with customer id -->
							<c:url var="updateLink" value="/updateForm">
								<c:param name="partId" value="${tempPart.id}" />
							</c:url>

							<!-- construct an "delete" link with customer id -->
							<c:url var="deleteLink" value="/delete">
								<c:param name="partId" value="${tempPart.id}" />
							</c:url>

							<tr>
								<td>${tempPart.namePart}</td>
								<c:if test="${tempPart.requirement == true}">
                                    <td>yes</td>
                                </c:if>
								<c:if test="${tempPart.requirement == false}">
                                    <td>no</td>
                                </c:if>
								<td>${tempPart.countInStock}</td>

								<td>
									<!-- display the update link --> <a href="${updateLink}">Update</a>
									| <a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this part?'))) return false">Delete</a>
								</td>

							</tr>

						</c:forEach>
						<tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
						</tr>

                        <tr>
                            <td>Can make </td>
                            <td>${maxCountPC}</td>
                            <td>${maxCountPC == 1 ? 'computer' : 'computers'}</td>
                            <td></td>
                        </tr>

					</table>
<div id="pagination">

    <c:url value="/list" var="prev">
        <c:param name="page" value="${page-1}"/>
        <c:if test="${search != null}">
            <c:param name="search" value="${search}"/>
        </c:if>
        <c:if test="${param.filter != null}">
            <c:param name="filter" value="${param.filter}"/>
        </c:if>
        <c:if test="${param.sort != null}">
            <c:param name="sort" value="${param.sort}"/>
        </c:if>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/list" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:if test="${search != null}">
                        <c:param name="search" value="${search}"/>
                    </c:if>
                    <c:if test="${param.filter != null}">
                        <c:param name="filter" value="${param.filter}"/>
                    </c:if>
                    <c:if test="${param.sort != null}">
                        <c:param name="sort" value="${param.sort}"/>
                    </c:if>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/list" var="next">
        <c:param name="page" value="${page + 1}"/>
            <c:if test="${search != null}">
                <c:param name="search" value="${search}"/>
            </c:if>
            <c:if test="${param.filter != null}">
                <c:param name="filter" value="${param.filter}"/>
            </c:if>
            <c:if test="${param.sort != null}">
                <c:param name="sort" value="${param.sort}"/>
            </c:if>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>
				</div>
			</div>
		</div>

	</div>
</body>

</html>









