# Hansung University 2015-2 Mobile Programming
> Thursday practice

### app
* Hello World!
* 프로젝트 생성 및 GitHub에 연동

### button
* 09.17 실습
* 버튼을 이용한 암시적 인텐트 사용

### calc
* 09.24 실습
* Button과 EditText 활용

### image
* 10.01 실습
* ImageView 와 ImageButton 활용

### calc2
* 10.08 실습
* TableLayout 또는 GridLayout을 이용한 계산기 앱 만들기

### webview
* 10.29 실습
* 간단한 웹 브라우져 만들기
* WebView를 이용
* EditText에서 주소를 입력 받고, 이동 버튼을 누르면 WebView에 해당 주소의 웹 페이지를 보여주며, 추가로 아래 내용을 구현한다
* 링크를 클릭하면 WebView내에서 보여주기 WebViewClient의 shouldOverrideUrlLoading() 메소드가 무조건 false를 리턴하게 함
* URL에 http:// 가 없으면 자동으로 붙여주기
* 페이지가 loading되면 해당URL을 EditText에 보여주기 (WebViewClient의 onPageFinished()메소드를 override해야 함. 그리고 View의 getRootView()메소드를 활용)

### dialog
* 11.05 실습
* Dialog를 이용하여 이름과, 이메일 주소 입력

### file
* 11.12 실습
* file 처리

### mydiary
* Homework1

### intent
* 11.19 실습
* Intent 활용

### adapter
* 11.26 실습
* AdapterView 활용
* 목록으로 되어 있는 어댑터(adpater)를 여러 형태(스피너, 리스트, 그리드)의 어댑터 뷰에 연결하여 보여주는 방법
* 어댑터가 어댑터 뷰에서 보여줄 각 항목의 View를 결정함
* 어댑터는 ArrayAdapter<>를 사용하거나 BaseAdapter를 상속하여 커스텀 어댑터를 만들어 사용함
* 어댑터 뷰에는 ListView, GridView, Spinner 등이 있음

### sqlite
* 12.03 실습
* SQLiteOpenHelper, SQLiteDatabase, Cursor를 사용하여 SQLite 활용
