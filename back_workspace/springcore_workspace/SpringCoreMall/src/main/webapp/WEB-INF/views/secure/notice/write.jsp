<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Notice Registration</title>

  <style>
    body {
      font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
      background-color: #f9f9f9;
      margin: 0;
      padding: 40px;
      color: #333;
    }

    h3 {
      text-align: center;
      font-weight: 500;
      color: #222;
      font-size: 28px;
      margin-bottom: 24px;
    }

    .container {
      width: 600px;
      margin: 0 auto;
      background-color: #fff;
      padding: 30px 40px;
      border-radius: 8px;
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.05);
    }

    label {
      font-size: 14px;
      font-weight: 500;
      color: #555;
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
      background-color: #fafafa;
      transition: border-color 0.3s ease;
    }

    input[type="text"]:focus,
    textarea:focus {
      outline: none;
      border-color: #888;
      background-color: #fff;
    }

    textarea {
      resize: vertical;
      min-height: 150px;
    }

    .submit-btn {
      display: block;
      width: 100%;
      background-color: #111;
      color: #fff;
      border: none;
      border-radius: 4px;
      padding: 14px;
      font-size: 15px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .submit-btn:hover {
      background-color: #000;
    }
  </style>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script>
    $(() => {
      $(".submit-btn").click(() => {
        $("form").attr({
          action: "/admin/notice/regist",
          method: "POST"
        });
        $("form").submit();
      });
    });
  </script>
</head>
<body>

  <h3>Register New Notice</h3>

  <div class="container">
    <form>
      <label for="title">Title</label>
      <input type="text" id="title" name="title" placeholder="Enter the title...">

      <label for="writer">Writer</label>
      <input type="text" id="writer" name="writer" placeholder="Enter the writer...">

      <label for="content">Content</label>
      <textarea id="content" name="content" placeholder="Write the content here..."></textarea>

      <input type="button" class="submit-btn" value="Submit">
    </form>
  </div>

</body>
</html>
