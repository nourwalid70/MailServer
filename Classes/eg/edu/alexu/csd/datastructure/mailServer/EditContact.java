package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.CInfo;
import eg.edu.alexu.csd.datastructure.mailServer.Contact;
import gui.Contacts.MyListModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditContact extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField textField;

	protected class MyListModel extends AbstractListModel<Object> {
		
		private DLinkedList myList = null;

		public MyListModel(DLinkedList list) {
			this.myList = list;
		}

		@Override
		public int getSize() {
			if (myList != null)
				return this.myList.size();
			return 0;
		}

		@Override
		public Object getElementAt(int index) {
			if (myList != null)
				return this.myList.get(index);
			return null;
		}

	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContact frame = new EditContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditContact(Contact current) {
		String[] arr = current.getAddresses();
		for(String str : arr) {
			System.out.println(str);
		}
		System.out.println();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		nameLabel.setBounds(61, 47, 78, 36);
		contentPane.add(nameLabel);
		
		nameTextField = new JTextField(current.getName());
		nameTextField.setBounds(145, 47, 264, 36);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblAddresses = new JLabel("Addresses :");
		lblAddresses.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblAddresses.setBounds(24, 126, 125, 36);
		contentPane.add(lblAddresses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 126, 264, 115);
		contentPane.add(scrollPane);
		
		JList list = new JList(current.getAddresses());
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(145, 276, 264, 36);
		contentPane.add(textField);
		
		JLabel lblAdd = new JLabel("add new address");
		lblAdd.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblAdd.setBounds(14, 276, 125, 36);
		contentPane.add(lblAdd);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = textField.getText();
				if(address.length() != 0) {
					current.setAddress(address);
				}
				//current.writeToFile();
				String[] arr = current.getAddresses();
				for(String str : arr) {
					System.out.println(str);
				}
				System.out.println();
				//list.setListData(current.getAddresses());
			}
		});
		addButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		addButton.setBounds(425, 276, 160, 36);
		contentPane.add(addButton);
		
		JButton btnDeleteSelected = new JButton("delete selected");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = list.getSelectedIndex();
				if(selected != -1) {
					current.removeAddress(selected);
					list.setListData(current.getAddresses());
				}
				//current.writeToFile();
				String[] arr = current.getAddresses();
				for(String str : arr) {
					System.out.println(str);
				}
				System.out.println();
			}
		});
		btnDeleteSelected.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnDeleteSelected.setBounds(425, 126, 160, 36);
		contentPane.add(btnDeleteSelected);
		
		JButton refreshButton = new JButton("refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setListData(current.getAddresses());
			}
		});
		refreshButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		refreshButton.setBounds(61, 359, 106, 31);
		contentPane.add(refreshButton);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//current.setName(s, "");
				dispose();
			}
		});
		btnConfirm.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnConfirm.setBounds(242, 359, 106, 31);
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnCancel.setBounds(430, 359, 106, 31);
		contentPane.add(btnCancel);
	}
}
