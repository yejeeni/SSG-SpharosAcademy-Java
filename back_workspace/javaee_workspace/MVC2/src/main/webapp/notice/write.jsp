<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>게시글 작성</title>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

<style>
  body {
    font-family: 'Inter', sans-serif;
    background-color: #f9fafb;
    padding: 40px 20px;
    margin: 0;
  }
  .container {
    max-width: 700px;
    margin: 0 auto;
    background: #fff;
    padding: 30px 40px;
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.07);
  }

  h3 {
    font-weight: 600;
    color: #333;
    margin-bottom: 30px;
    font-size: 1.8rem;
    text-align: center;
  }

  label {
    font-weight: 500;
    color: #555;
    display: block;
    margin-bottom: 8px;
    margin-top: 20px;
  }

  input[type="text"], textarea {
    width: 100%;
    padding: 14px 16px;
    border: 1.5px solid #d1d5db;
    border-radius: 10px;
    font-size: 1rem;
    transition: border-color 0.25s ease;
    resize: vertical;
    box-sizing: border-box;
  }

  input[type="text"]:focus,
  textarea:focus {
    outline: none;
    border-color: #4b7bec;
    box-shadow: 0 0 0 3px rgba(75,123,236,0.2);
  }

  textarea {
    min-height: 160px;
    font-family: 'Inter', sans-serif;
  }

  input[type="button"] {
    background-color: #4b7bec;
    color: white;
    border: none;
    border-radius: 12px;
    padding: 14px 30px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    margin-top: 30px;
    display: block;
    width: 100%;
    transition: background-color 0.3s ease;
  }

  input[type="button"]:hover {
    background-color: #3a5fc1;
  }

  /* 모바일 대응 */
  @media (max-width: 480px) {
    .container {
      padding: 20px;
    }

    h3 {
      font-size: 1.5rem;
    }
  }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
  $(() => {
    $("input[type='button']").click(() => {
      $("form").attr({
        action: "/notice/regist.do",
        method: "POST",
      });
      $("form").submit();
    });
  });
</script>
</head>
<body>

<div class="container">
  <h3>📋 게시글 작성하기</h3>
  <form>
    <label for="fname">제목</label>
    <input type="text" id="fname" name="title" placeholder="제목을 입력하세요" required>

    <label for="lname">작성자</label>
    <input type="text" id="lname" name="writer" placeholder="작성자명을 입력하세요" required>

    <label for="content">내용</label>
    <textarea id="content" name="content" placeholder="내용을 입력하세요" required></textarea>

    <input type="button" value="등록하기">
  </form>
</div>

</body>
</html>
