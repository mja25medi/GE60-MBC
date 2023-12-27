<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.edutrack.Constants"%>
<%@ page import="egovframework.edutrack.comm.util.security.KISASeed"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%=KISASeed.seed256HashEncryption("1234")%>