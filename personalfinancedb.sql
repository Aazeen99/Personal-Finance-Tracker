-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2024 at 08:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `personalfinancedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `userId` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `amount` varchar(200) NOT NULL,
  `bill` varchar(200) NOT NULL,
  `duedate` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`userId`, `username`, `amount`, `bill`, `duedate`) VALUES
(1, 'konain312', '2400', 'electricity bill', '24-05-2024'),
(2, 'konain312', '700', 'gas bill', '19-05-2024');

-- --------------------------------------------------------

--
-- Table structure for table `budget`
--

CREATE TABLE `budget` (
  `id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `amount` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `budget`
--

INSERT INTO `budget` (`id`, `username`, `category`, `amount`) VALUES
(1, 'konain312', 'Groceries', '35'),
(2, 'konain312', 'Bills', '60'),
(3, 'konain312', 'Bills', '30'),
(4, 'konain312', 'Groceries', '20'),
(5, 'konain312', 'Entertainment', '15'),
(6, 'konain312', 'Others', '35'),
(7, 'aazeenhassan', 'Groceries', '50'),
(8, 'aazeenhassan', 'Bills', '60');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `username` varchar(200) DEFAULT NULL,
  `amount` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `payment_method` varchar(200) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`username`, `amount`, `category`, `payment_method`, `userId`) VALUES
(NULL, '2000.0', 'groceries', 'cash', 1),
('konain312', '4500.0', 'bills', 'credit card', 2),
('konain312', '3000.0', 'entertainment', 'cash', 3),
('aazeenhassan', '5000.0', 'groceries', 'credit card', 4),
('konain312', '7500.0', 'entertainment', 'cash', 5);

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE `income` (
  `userId` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `amount` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`userId`, `username`, `amount`, `description`) VALUES
(1, 'konain312', '250000', 'salary'),
(2, 'khadija123', '300000', 'freelance'),
(3, 'aazeen123', '5000000', 'haram da paisa'),
(4, 'konain312', '800000', 'Business Profit'),
(5, 'amna', '999999999999999999999999999$', 'kala paisa');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `salt` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `email`, `password`, `salt`) VALUES
('aazeen123', 'aazeen123@gmail.com', '9Fnq7YMncdkbgsqFSAxIO7XHqOxX5b40nOjRJ9oWDoI=', 'gxlPyMBkjyLDR1IjuzlNJA=='),
('aazeenhassan', 'aazeenhassan@gmail.com', 'tTbI82lTJ2Ag1/cr9HNkp4wAG7XEHEJ0rRoAMS7WabA=', 'kVm92OfCJUVhxlmmCHsTDA=='),
('alyankk', 'alyankhattak@gmail.com', 'yMuu3eOc4JDgAAj2ZfPp+ivFNgdeH6AsmKuLujjI7io=', '82dEBtpNNKaL7VWuBPUOaw=='),
('amna', 'amnashafiq1803@gmail.com', 'HKYA1eCcJm6Wbmv7D3N7IIx4Nt8gRB2TmSNuXtj/G0s=', 'MrR1dqwUZtpDSzFaeY6tbQ=='),
('khadija123', 'khadijabibi@gmail.com', '8Lpza1BlI4fXF6PoWchY0AfJuvmX3A0gd9iybBOpDQ8=', '4gjGg7ETJXXkHsw23tUuBQ=='),
('konain312', 'konain.raza312@gmail.com', 'lL2YvmPi9ZvPzwZKagiTK4Faju6ENVH2f2himQv02JI=', 'wzByQTPOB44+1o1x5y1zVA==');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `budget`
--
ALTER TABLE `budget`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `budget`
--
ALTER TABLE `budget`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `income`
--
ALTER TABLE `income`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
