--
-- Table structure for table `user`
--

CREATE TABLE `user` (
`id` bigint(20) NOT NULL,
`email` varchar(45) NOT NULL,
`firstname` varchar(45) NOT NULL,
`lastname` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;