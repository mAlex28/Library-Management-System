CREATE DATABASE `Library_Management_System` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `admin` (
  `adminid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `securityQ` varchar(25) DEFAULT NULL,
  `securityA` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `book` (
  `bookid` int(10) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(15) DEFAULT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `publisher` varchar(30) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  `nopages` varchar(10) DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `bookIssue` (
  `bookid` int(10) DEFAULT NULL,
  `isbn` varchar(15) NOT NULL,
  `stuid` int(10) DEFAULT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `stuname` varchar(40) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `branch` varchar(20) DEFAULT NULL,
  `dateOfIssue` varchar(100) DEFAULT NULL,
  KEY `FK_bookId` (`bookid`),
  KEY `FK_stuId` (`stuid`),
  CONSTRAINT `FK_bookId` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`),
  CONSTRAINT `FK_stuId` FOREIGN KEY (`stuid`) REFERENCES `student` (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `bookReturn` (
  `bookid` int(10) DEFAULT NULL,
  `isbn` varchar(15) NOT NULL,
  `stuid` int(10) DEFAULT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `stuname` varchar(40) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `branch` varchar(20) DEFAULT NULL,
  `dateOfIssue` varchar(100) DEFAULT NULL,
  `dateOfReturn` varchar(30) DEFAULT NULL,
  KEY `FK_bookRId` (`bookid`),
  KEY `FK_stuRId` (`stuid`),
  CONSTRAINT `FK_bookRId` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`),
  CONSTRAINT `FK_stuRId` FOREIGN KEY (`stuid`) REFERENCES `student` (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
  `stuid` int(10) NOT NULL AUTO_INCREMENT,
  `stufname` varchar(40) DEFAULT NULL,
  `stulname` varchar(40) DEFAULT NULL,
  `course` varchar(20) DEFAULT NULL,
  `branch` varchar(40) DEFAULT NULL,
  `year` int(5) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;



