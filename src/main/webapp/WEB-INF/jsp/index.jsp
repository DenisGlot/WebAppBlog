<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h3>
	Такой структурированный результ агрегирования можно получить на примере
	сайта <a href="https://www.javaworld.com/category/core-java"
		target="_blank">https://www.javaworld.com/category/core-java</a>
</h3>


<table class="table table-bordered table-hover table-striped">

	<thead>
		<tr>
			<th>Date</th>
			<th>Item</th>
		</tr>

	</thead>
	<tbody>
		<c:forEach items="${items}" var="item">
			<tr>
				<td><c:out value="${item.publishedDate}" /></td>
				<td><strong> <a href="<c:out value="${item.link}"/>"
						target="_blank"> <c:out value="${item.title}" />
					</a>
				</strong> <br /> <c:out value="${item.textHtml == null ? ' ' : item.textHtml}" /></td>
			</tr>
		</c:forEach>
	</tbody>

</table>