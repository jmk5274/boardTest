<%@page import="kr.or.ddit.board.service.IBoardService"%>
<%@page import="kr.or.ddit.board.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.board.model.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	IBoardService serv = BoardServiceImpl.getInstance();
	List<BoardVo> boardList = serv.getBoardList();
	request.setAttribute("boardList", boardList);
%>

   <ul class="nav nav-sidebar">
   		<li class="active"><a href="${cp }/addBoard">게시판 생성 <span class="sr-only">(current)</span></a></li>
   		<br>
        <c:forEach items="${boardList }" var="list">
        	<c:if test="${list.usestatus == 'Y' }">
 		       	<li class="active"><a href="${cp }/post?boardNum=${list.boardnum}&boardNm=${list.boardnm}">${list.boardnm} <span class="sr-only">(current)</span></a></li>
        	</c:if>
        </c:forEach>
   </ul>