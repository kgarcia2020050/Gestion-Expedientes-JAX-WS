CREATE DATABASE case_management_system;
GO

USE case_management_system;
GO

CREATE TABLE [user] (
    id INT PRIMARY KEY IDENTITY(1,1),
    email NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL, -- Encrypted
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
    last_login DATETIME,
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
    active BIT DEFAULT 1,
	role VARCHAR(50) DEFAULT 'EMPLOYEE'
);
GO

CREATE TABLE company (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    address NVARCHAR(255),
    phone NVARCHAR(20),
    email NVARCHAR(100),
    id_identification NVARCHAR(50),
    economic_activity NVARCHAR(100),
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE department (
    id INT PRIMARY KEY IDENTITY(1,1),
    company_id INT FOREIGN KEY REFERENCES company(id),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE employee (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT FOREIGN KEY REFERENCES [user](id),
    company_id INT FOREIGN KEY REFERENCES company(id),
    department_id INT FOREIGN KEY REFERENCES department(id),
    status NVARCHAR(50) DEFAULT 'active', -- 'active', 'inactive', 'blocked'
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE workflow (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    version INT DEFAULT 1, -- Version control
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE administrative_process (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    workflow_id INT FOREIGN KEY REFERENCES workflow(id),
    priority NVARCHAR(50) NOT NULL, -- 'High', 'Medium', 'Low'
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE workflow_department (
    id INT PRIMARY KEY IDENTITY(1,1),
    workflow_id INT FOREIGN KEY REFERENCES workflow(id),
    department_id INT FOREIGN KEY REFERENCES department(id),
    step INT NOT NULL, -- Indicates the order of departments in the workflow
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE case_status (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(50) NOT NULL,
    description NVARCHAR(255),
    created_by INT,
    updated_by INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
	    active BIT DEFAULT 1,
);
GO

CREATE TABLE case_file (
    id INT PRIMARY KEY IDENTITY(1,1),
    process_id INT FOREIGN KEY REFERENCES administrative_process(id),
    created_by INT,
    updated_by INT,
    status_id INT FOREIGN KEY REFERENCES case_status(id),
    description NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
    start_date DATETIME,
    estimated_end_date DATETIME,
    end_date DATETIME,
    total_departments INT,
    completed_departments INT DEFAULT 0,
    workflow_version INT,
    additional_data VARCHAR(MAX), -- Custom data specific to each case file,
	  active BIT DEFAULT 1,
);
GO

CREATE TABLE case_tracking (
    id INT PRIMARY KEY IDENTITY(1,1),
    case_file_id INT FOREIGN KEY REFERENCES case_file(id),
    action_performed_by INT FOREIGN KEY REFERENCES [user](id),
    department_id INT FOREIGN KEY REFERENCES department(id), 
    comments NVARCHAR(500),
    attached_documents NVARCHAR(500),
    transfer_date DATETIME DEFAULT GETDATE(),
    deadline DATETIME,
    delayed BIT DEFAULT 0,
	  active BIT DEFAULT 1,
);
GO
