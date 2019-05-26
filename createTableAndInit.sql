USE test;
DROP table if exists part;

create table part (
	id INT(11) NOT NULL AUTO_INCREMENT,
	namePart VARCHAR(255) NOT NULL,
    requirement TINYINT(1) NOT NULL,
    countInStock INT NOT NULL,
	PRIMARY KEY (id))
    ENGINE = InnoDB
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO part (namePart, requirement, countInStock) values ("Motherboard", true, 30),
	("CPU", true, 40),
    ("CPU cooler", true, 27),
    ("RAM", true, 34),
    ("HDD disk", false, 122),
    ("SSD disk", true, 119),
    ("Computer case", true, 45),
    ("Computer case light", false, 66),
    ("DVD optical drive", false, 42),
    ("Power converter", true, 85),
    ("Power cable", true, 120),
	("Video card", true, 38),
	("Sound card", false, 98),
    ("Microphone", false, 4),
    ("TV tuner card", false, 2),
	("Modem", false, 2),
    ("WiFi card", false, 6),
    ("Ethernet card", true, 73),
    ("Card reader", false, 5),
    ("Keyboard", false, 120),
    ("Computer mouse", false, 120),
    ("Joystick", false, 0);