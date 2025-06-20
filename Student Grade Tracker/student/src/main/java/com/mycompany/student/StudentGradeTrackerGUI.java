/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student;

/**
 *
 * @author ritik
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField nameField, gradeField;
    private JTextArea outputArea;
    private JButton addStudentBtn, addGradeBtn, showStatsBtn;
    private Student student;

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Student name input
        add(new JLabel("Student Name:"));
        nameField = new JTextField(20);
        add(nameField);

        addStudentBtn = new JButton("Add Student");
        add(addStudentBtn);

        // Grade input
        add(new JLabel("Enter Grade:"));
        gradeField = new JTextField(5);
        add(gradeField);

        addGradeBtn = new JButton("Add Grade");
        add(addGradeBtn);
        addGradeBtn.setEnabled(false); // Disabled until student is added

        showStatsBtn = new JButton("Show Stats");
        add(showStatsBtn);
        showStatsBtn.setEnabled(false); // Disabled until grades are added

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // Action: Add Student
        addStudentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    student = new Student(name);
                    outputArea.setText("✅ Student added: " + name + "\n");
                    addGradeBtn.setEnabled(true);
                    showStatsBtn.setEnabled(true);
                    addStudentBtn.setEnabled(false); // Prevent re-adding
                    nameField.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a student name.");
                }
            }
        });

        // Action: Add Grade
        addGradeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (student == null) {
                    JOptionPane.showMessageDialog(null, "Add student first.");
                    return;
                }

                String gradeText = gradeField.getText().trim();
                try {
                    int grade = Integer.parseInt(gradeText);
                    if (grade >= 0 && grade <= 100) {
                        student.addGrade(grade);
                        outputArea.append("➕ Grade added: " + grade + "\n");
                        gradeField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Grade must be between 0 and 100.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });

        // Action: Show Stats
        showStatsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (student != null && !student.getGrades().isEmpty()) {
                    outputArea.append("\n📊 --- Grade Summary ---\n");
                    outputArea.append("Grades: " + student.getGrades() + "\n");
                    outputArea.append("Average: " + student.getAverage() + "\n");
                    outputArea.append("Highest: " + student.getHighest() + "\n");
                    outputArea.append("Lowest: " + student.getLowest() + "\n\n");
                } else {
                    JOptionPane.showMessageDialog(null, "No grades to show.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentGradeTrackerGUI();
    }
}