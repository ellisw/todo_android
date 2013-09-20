package com.example.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {
	private ArrayList<String> todoItems;
	private ArrayAdapter<String> aTodoItems;
	private ListView lvItems;
	private EditText etNewItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		lvItems = (ListView) findViewById(R.id.lvItems);
		etNewItem = (EditText) findViewById(R.id.etNewItem);
		readItems();
		aTodoItems = new ListWithButtonAdaptor(this, R.layout.item_row, todoItems);
		lvItems.setAdapter(aTodoItems);
		setupListViewListener();
	}

	private void setupListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View item, int pos, long id) {
				todoItems.remove(pos);
				aTodoItems.notifyDataSetChanged();
				writeItems();
				return true;
			}
		});
	}
	
	private void readItems() {
		File filesDir =  getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			todoItems = new ArrayList<String> (FileUtils.readLines(todoFile));
		} catch (IOException e) {
			todoItems = new ArrayList<String>();
		}
	}

	private void writeItems() {
		File filesDir =  getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, todoItems);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onAddItem(View v) {
		String itemText = etNewItem.getText().toString();
		aTodoItems.add(itemText);
		etNewItem.setText("");
		writeItems();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

}
