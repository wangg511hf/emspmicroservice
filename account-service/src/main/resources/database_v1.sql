-- Create emsp database
CREATE DATABASE emsp;

-- Create database administrator
CREATE USER 'emsp_user'@'localhost' IDENTIFIED BY 'emsp_password';
GRANT ALL PRIVILEGES ON emsp.* TO 'emsp_user'@'localhost';
FLUSH PRIVILEGES;

-- Table schema & data for emsp_account
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emsp_account
-- ----------------------------
DROP TABLE IF EXISTS `emsp_account`;
CREATE TABLE `emsp_account`  (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `account_status` enum('CREATED','ACTIVATED','DEACTIVATED') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CREATED' COMMENT '\'CREATED,ACTIVATED,DEACTIVATED\'',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE INDEX `idx_email`(`email` ASC) USING BTREE,
                                 UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emsp_account
-- ----------------------------
INSERT INTO `emsp_account` VALUES (1, 't4455@gmail.com', 'Duncan', 'tj1231', 'Dc3554123', 'ACTIVATED', '2025-03-31 23:33:17', '2025-04-01 00:39:14');
INSERT INTO `emsp_account` VALUES (2, 'jgy58328@163.com', 'Jim', 'Jim12378has', 'Jmh123054', 'DEACTIVATED', '2025-03-31 23:40:39', '2025-04-02 12:43:35');
INSERT INTO `emsp_account` VALUES (5, 'Wf1isd8f09@126.com', 'Wolf', 'Waz2390887', 'WWooo9012386767', 'ACTIVATED', '2025-03-31 23:57:47', '2025-04-02 12:49:58');
INSERT INTO `emsp_account` VALUES (6, 'Ll1111@126.com', 'Lily', 'Lily12345', 'Ll12345678', 'ACTIVATED', '2025-04-01 00:00:00', '2025-04-01 00:39:30');
INSERT INTO `emsp_account` VALUES (7, 'lucy123@gmail.com', 'Lucy', 'lu123456', 'Ly45732154', 'DEACTIVATED', '2025-04-01 00:44:12', '2025-04-01 00:44:12');
INSERT INTO `emsp_account` VALUES (8, 'frankff1123@gmail.com', 'Frank', 'fr123456', 'Fk5416767', 'DEACTIVATED', '2025-04-01 17:39:57', '2025-04-01 23:13:27');
INSERT INTO `emsp_account` VALUES (9, 'Emma123@gmail.com', 'Emma', 'emma666666', 'Emma123456!', 'CREATED', '2025-04-01 22:57:47', '2025-04-01 22:57:47');

SET FOREIGN_KEY_CHECKS = 1;


-- Table schema & data for emsp_card
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emsp_card
-- ----------------------------
DROP TABLE IF EXISTS `emsp_card`;
CREATE TABLE `emsp_card`  (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `card_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                              `account_id` int NULL DEFAULT NULL,
                              `contract_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
                              `card_status` enum('CREATED','ASSIGNED','ACTIVATED','DEACTIVATED') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CREATED' COMMENT '\'CREATED\',\'ASSIGNED\',\'ACTIVATED\',\'DEACTIVATED\'',
                              `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `idx_card_num`(`card_num` ASC) USING BTREE,
                              INDEX `idx_account_id`(`account_id` ASC) USING BTREE,
                              CONSTRAINT `fk_card_account` FOREIGN KEY (`account_id`) REFERENCES `emsp_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emsp_card
-- ----------------------------
INSERT INTO `emsp_card` VALUES (2, '1234567890987654321', 6, 'KR-AB-3731615370', 'ASSIGNED', '2025-04-01 10:13:31', '2025-04-01 10:13:31');
INSERT INTO `emsp_card` VALUES (4, '8744569915367522', 8, 'JP-KL-9116919166', 'ACTIVATED', '2025-04-01 10:39:29', '2025-04-02 12:51:15');
INSERT INTO `emsp_card` VALUES (5, '6523147752687465', 1, 'CN-AB-1234567890', 'ASSIGNED', '2025-04-01 11:02:43', '2025-04-01 11:02:43');
INSERT INTO `emsp_card` VALUES (6, '6611672888888888', 7, 'CN-CD-9872612578', 'DEACTIVATED', '2025-04-01 11:03:50', '2025-04-02 11:36:25');

SET FOREIGN_KEY_CHECKS = 1;