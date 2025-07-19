<%@page import="notice.domain.Notice"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
  Notice notice = (Notice)session.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Edit Notice</title>

  <!-- 스타일 영역 -->
  <style>
    body {
      font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
      background-color: #f7f7f7;
      margin: 0;
      padding: 40px;
      color: #333;
    }

    h3 {
      text-align: center;
      font-size: 28px;
      font-weight: 500;
      color: #222;
      margin-bottom: 30px;
    }

    .container {
      width: 700px;
      margin: 0 auto;
      background-color: #fff;
      padding: 30px 40px;
      border-radius: 8px;
      box-shadow: 0 6px 18px rgba(0, 0, 0, 0.05);
    }

    label {
      font-size: 14px;
      font-weight: 500;
      color: #444;
      margin-bottom: 6px;
      display: block;
    }

    input[type="text"],
    textarea {
      width: 100%;
      padding: 12px 14px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 14px;
      background-color: #fdfdfd;
    }

    textarea {
      resize: vertical;
    }

    .button-group {
      display: flex;
      gap: 10px;
    }

    .btn {
      flex: 1;
      padding: 12px 0;
      background-color: #111;
      color: #fff;
      border: none;
      border-radius: 4px;
      font-size: 14px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .btn:hover {
      background-color: #000;
    }

    .note-editor {
      margin-bottom: 20px;
    }
  </style>

  <!-- 라이브러리 + 서머노트 -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

  <!-- JS 이벤트 설정 -->
  <script>
    $(() => {
      $("#content").summernote({
        height: 250
      });
      $("#content").summernote('code', `<%=notice.getContent()%>`);

      $("#bt_edit").click(() => {
        if (confirm("수정하시겠어요?")) {
          $("form").attr({
            method: "POST",
            action: "/notice/update"
          });
          $("form").submit();
        }
      });

      $("#bt_del").click(() => {
        if (confirm("삭제하시겠어요?")) {
          location.href = "/notice/del?notice_id=<%=notice.getNotice_id()%>";
        }
      });

      $(
