package me.dong.dialog;

import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvDialogName, tvDialogEmail, tvToast;
    Button btnShowDialog;
    EditText etName, etEmail, etDialogName, etDialogEmail;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);
        btnShowDialog = (Button) findViewById(R.id.button_show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);

                etDialogName = (EditText) dialogView.findViewById(R.id.editText_dialog_name);
                etDialogEmail = (EditText) dialogView.findViewById(R.id.editText_dialog_email);

                etDialogName.setText(etName.getText().toString());
                etDialogEmail.setText(etEmail.getText().toString());

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("사용자 정보 입력")
                        .setIcon(R.drawable.ic_group_black_24dp)
                        .setView(dialogView)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                etName.setText(etDialogName.getText().toString());
                                etEmail.setText(etDialogEmail.getText().toString());
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast toast = new Toast(MainActivity.this);
                                toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                                tvToast = (TextView) toastView.findViewById(R.id.textView_toast);
                                tvToast.setText("최소했습니다.");
                                toast.setView(toastView);

                                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                                Point displayPoint = new Point();
                                display.getSize(displayPoint);

                                int xOffset = (int)(Math.random() * displayPoint.x);
                                int yOffset = (int)(Math.random() * displayPoint.y);

                                toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset, yOffset);
                                toast.show();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
