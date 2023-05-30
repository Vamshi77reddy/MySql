
UC1-Ability to create a payroll
        service database

        mysql> show database;
        ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'database' at line 1
        mysql> show databases;
        +--------------------+
        | Database           |
        +--------------------+
        | addressbok         |
        | addressbook        |
        | demo_db            |
        | information_schema |
        | mysql              |
        | payroll_service    |
        | payroll_services   |
        | performance_schema |
        | sakila             |
        | sys                |
        | world              |
        +--------------------+
        11 rows in set (0.00 sec)

        UC2-Ability to create a employee payroll table in the payroll service database to manage employee payrolls;


        mysql> use payroll_services;
        Database changed
        mysql> desc employee_payroll;
        +------------+-------------+------+-----+---------+----------------+
        | Field      | Type        | Null | Key | Default | Extra          |
        +------------+-------------+------+-----+---------+----------------+
        | ID         | int         | NO   | PRI | NULL    | auto_increment |
        | name       | varchar(50) | YES  |     | NULL    |                |
        | salary     | int         | YES  |     | NULL    |                |
        | start_date | date        | YES  |     | NULL    |                |
        +------------+-------------+------+-----+---------+----------------+
        4 rows in set (0.00 sec)

        UC3-Ability to create employee payroll data in the payroll service database;

        mysql> insert into employee_payroll(name,salary,start_date)
        -> values("vamshi",6000000.0,'2022-06-12'),
        -> ("sai",8000000.0,'2022-04-21'),
        -> ("rani",7000000.0,'2022-09-19'),
        -> ("harshita",6000000.0,'2023-07-21');
        Query OK, 4 rows affected (0.01 sec)
        Records: 4  Duplicates: 0  Warnings: 0


        UC4-Retrive data from database
        mysql> select * from employee_payroll;
        +----+----------+---------+------------+
        | ID | name     | salary  | start_date |
        +----+----------+---------+------------+
        |  1 | vamshi   |  200000 | 2021-11-22 |
        |  2 | vamshi   | 6000000 | 2022-06-12 |
        |  3 | sai      | 8000000 | 2022-04-21 |
        |  4 | rani     | 7000000 | 2022-09-19 |
        |  5 | harshita | 6000000 | 2023-07-21 |
        +----+----------+---------+------------+
        5 rows in set (0.00 sec)


        UC5-Ability to retrieve salary data for a particular employee as well as all employees who have joined in a particular data range from the payroll service database

        mysql> where name = "harshita";
        ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'where name = "harshita"' at line 1
        mysql> select * from employee_payroll;
        +----+----------+---------+------------+
        | ID | name     | salary  | start_date |
        +----+----------+---------+------------+
        |  1 | vamshi   |  200000 | 2021-11-22 |
        |  2 | vamshi   | 6000000 | 2022-06-12 |
        |  3 | sai      | 8000000 | 2022-04-21 |
        |  4 | rani     | 7000000 | 2022-09-19 |
        |  5 | harshita | 6000000 | 2023-07-21 |
        +----+----------+---------+------------+
        5 rows in set (0.00 sec)


        UC6-Ability to add Gender to Employee Payroll Table and Update the Rows to reflect the correct Employee Gender
        where start between cast('2022-06-12' as date)  and date(now());


        mysql> alter table employee_payroll add gender varchar(10) after name;
        Query OK, 0 rows affected (0.04 sec)
        Records: 0  Duplicates: 0  Warnings: 0

        mysql> update employee_payroll
        -> set gender = "Male"
        -> where id < 3;
        Query OK, 2 rows affected (0.00 sec)
        Rows matched: 2  Changed: 2  Warnings: 0

        mysql> update employee_payroll
        -> set gender = "Female"
        -> where id > 2;
        Query OK, 3 rows affected (0.00 sec)
        Rows matched: 3  Changed: 3  Warnings: 0

        mysql> update employee_payroll
        -> set gender = "Female"
        -> where id = 3;
        Query OK, 0 rows affected (0.00 sec)
        Rows matched: 1  Changed: 0  Warnings: 0

        mysql> select * from employee_payroll;
        +----+----------+--------+---------+------------+
        | ID | name     | gender | salary  | start_date |
        +----+----------+--------+---------+------------+
        |  1 | vamshi   | Male   |  200000 | 2021-11-22 |
        |  2 | vamshi   | Male   | 6000000 | 2022-06-12 |
        |  3 | sai      | Female | 8000000 | 2022-04-21 |
        |  4 | rani     | Female | 7000000 | 2022-09-19 |
        |  5 | harshita | Female | 6000000 | 2023-07-21 |
        +----+----------+--------+---------+------------+
        5 rows in set (0.00 sec)


        UC7-Ability to find sum, average, min, max and number of male and female employees

        mysql> select sum(salary) from employee_payroll
        -> where gender = "Male" group by gender;
        +-------------+
        | sum(salary) |
        +-------------+
        |     6200000 |
        +-------------+
        1 row in set (0.01 sec)

        mysql> select avg(salary) from employee_payroll
        -> where gender = "Female" group by gender;
        +--------------+
        | avg(salary)  |
        +--------------+
        | 7000000.0000 |
        +--------------+
        1 row in set (0.00 sec)

        mysql> select min(salary) from employee_payroll
        -> where gender = "Female" group by gender;
        +-------------+
        | min(salary) |
        +-------------+
        |     6000000 |
        +-------------+
        1 row in set (0.00 sec)

        mysql> select max(salary) from employee_payroll
        -> where gender = "Male" group by gender;
        +-------------+
        | max(salary) |
        +-------------+
        |     6000000 |
        +-------------+
        1 row in set (0.00 sec)

        mysql> select count(id) from employee_payroll
        -> where gender = "Male" group by gender;
        +-----------+
        | count(id) |
        +-----------+
        |         2 |
        +-----------+
        1 row in set (0.00 sec)

        mysql> select count(id) from employee_payroll
        -> where gender = "Female" group by gender;
        +-----------+
        | count(id) |
        +-----------+
        |         3 |
        +-----------+
        1 row in set (0.00 sec)


        UC8-Ability to extend employee_payroll data to store employee information like employee phone, address and department - Ensure employee department

        mysql> alter table employee_payroll add phonenumber long after start_date;
        Query OK, 0 rows affected (0.02 sec)
        Records: 0  Duplicates: 0  Warnings: 0

        mysql> alter table employee_payroll add address varchar(100) after phonenumber;
        Query OK, 0 rows affected (0.02 sec)
        Records: 0  Duplicates: 0  Warnings: 0

        mysql> alter table employee_payroll add department varchar(50) after salary;
        ERROR 1060 (42S21): Duplicate column name 'department'
        mysql> update employee_payroll set department = 'java', phonenumber = 77845362517 , address = "chennai"
        -> where id = 1;
        Query OK, 1 row affected (0.00 sec)
        Rows matched: 1  Changed: 1  Warnings: 0

        mysql> update employee_payroll set department = 'Deskservice', phonenumber = 77534192837 , address = "hyderabad"
        -> where id = 2;
        Query OK, 1 row affected (0.00 sec)
        Rows matched: 1  Changed: 1  Warnings: 0

        mysql> update employee_payroll set department = 'developer', phonenumber = 7625273827 , address = "pune"
        -> where id = 3;
        Query OK, 1 row affected (0.00 sec)
        Rows matched: 1  Changed: 1  Warnings: 0

        mysql> update employee_payroll set department = 'Tester', phonenumber = 88765432678 , address = "hyderabad"
        -> where id = 4;
        Query OK, 1 row affected (0.00 sec)
        Rows matched: 1  Changed: 1  Warnings: 0

        mysql> select * from employee_payroll;
        +----+----------+--------+---------+-------------+------------+-------------+-----------+
        | ID | name     | gender | salary  | department  | start_date | phonenumber | address   |
        +----+----------+--------+---------+-------------+------------+-------------+-----------+
        |  1 | vamshi   | Male   |  200000 | java        | 2021-11-22 | 77845362517 | chennai   |
        |  2 | vamshi   | Male   | 6000000 | Deskservice | 2022-06-12 | 77534192837 | hyderabad |
        |  3 | sai      | Female | 8000000 | developer   | 2022-04-21 | 7625273827  | pune      |
        |  4 | rani     | Female | 7000000 | Tester      | 2022-09-19 | 88765432678 | hyderabad |
        |  5 | harshita | Female | 6000000 | NULL        | 2023-07-21 | NULL        | NULL      |
        +----+----------+--------+---------+-------------+------------+-------------+-----------+
        5 rows in set (0.00 sec)

        mysql>

