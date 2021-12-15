
INSERT INTO addonis.roles (id, role_name) VALUES (1, 'Admin');
INSERT INTO addonis.roles (id, role_name) VALUES (2, 'User');

INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (1, 'IntellijIDE', 'intellij.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (2, 'Eclipse', 'eclipse.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (3, 'Visual Studio', 'studio.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (4, 'PyCharm', 'pycharm.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (5, 'Xcode', 'xcode.png');

INSERT INTO addonis.tags (id, name) VALUES (12, 'Autocomplete');
INSERT INTO addonis.tags (id, name) VALUES (3, 'Coding');
INSERT INTO addonis.tags (id, name) VALUES (17, 'Configuration');
INSERT INTO addonis.tags (id, name) VALUES (7, 'Dark');
INSERT INTO addonis.tags (id, name) VALUES (2, 'Databases');
INSERT INTO addonis.tags (id, name) VALUES (18, 'Editor');
INSERT INTO addonis.tags (id, name) VALUES (20, 'Formatter');
INSERT INTO addonis.tags (id, name) VALUES (10, 'GIT');
INSERT INTO addonis.tags (id, name) VALUES (11, 'Open Source');
INSERT INTO addonis.tags (id, name) VALUES (13, 'Power tools');
INSERT INTO addonis.tags (id, name) VALUES (15, 'Productivity');
INSERT INTO addonis.tags (id, name) VALUES (1, 'Refactoring');
INSERT INTO addonis.tags (id, name) VALUES (19, 'Resources');
INSERT INTO addonis.tags (id, name) VALUES (16, 'Scripting');
INSERT INTO addonis.tags (id, name) VALUES (4, 'Settings');
INSERT INTO addonis.tags (id, name) VALUES (9, 'Shell');
INSERT INTO addonis.tags (id, name) VALUES (5, 'Text edit');
INSERT INTO addonis.tags (id, name) VALUES (8, 'Theme');
INSERT INTO addonis.tags (id, name) VALUES (14, 'Validation');
INSERT INTO addonis.tags (id, name) VALUES (6, 'Visuals');

INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (1, 'admin', 'pass', 'Akita', 'Inu', 'akita@gmail.com', 'dogecoin.jpeg-900x510-big.jpg', 0, 1, '0898751117', 1);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (2, 'kalin', 'BBBb123!', 'Kalin', 'Nachev', 'kalin.nachev@gmail.com', 'IMG_9497.png', 0, 2, '0898758267', 1);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (4, 'gabi', 'Gab123^&&', 'Gabriela', 'Georgieva', 'gabigeorgieva@abv.bg', '254167383_933997764182280_5456277152055192074_n.jpg', 1, 2, '0898708810', 1);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (20, 'Borkata', 'V@lid123', 'Borislav', 'Ignatov', 'boignatov@tu-sofia.bg', '264433552-256-k270892.jpg', 0, 2, '0899879546', 1);

INSERT INTO addonis.verifications_token (id, user_id, name) VALUES (1, 20, '816a0249-6900-412e-8fcf-fb05fec30aa7');
INSERT INTO addonis.verifications_token (id, user_id, name) VALUES (2, 1, 'fc934a0b-5c69-44c0-aa15-699a8b3da088');
INSERT INTO addonis.verifications_token (id, user_id, name) VALUES (3, 2, '2c2505e9-0f7f-4ae4-9da2-5e896eb45b71');

INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (24, 'Update README.md', 6, 7, '2021-11-16 01:36:16', '2021-12-15 14:00:01');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (27, 'm', 1, 1, '2021-03-04 03:07:27', '2021-12-15 14:00:02');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (29, 'Add base directory to search path for csx/csxe (#2945)', 4, 211, '2021-12-06 18:19:53', '2021-12-15 14:00:04');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (31, 'Various fixes to theme tokens.
Fixes #3', 0, 6, '2021-09-22 18:09:00', '2021-12-15 14:00:04');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (34, 'Adding more exclusion', 1, 0, '2019-05-24 13:11:49', '2021-12-15 14:00:05');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (35, 'Test', 15, 378, '2021-10-28 18:39:56', '2021-12-15 14:00:07');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (38, 'Enable debug', 0, 6, '2021-11-07 15:29:51', '2021-12-15 14:00:08');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (41, 'V 1.62', 3, 52, '2021-11-21 13:23:10', '2021-12-15 14:00:09');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (43, 'Test', 19, 31, '2021-11-09 09:11:29', '2021-12-15 14:00:11');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (44, 'Test', 100, 1229, '2021-12-15 10:53:53', '2021-12-15 14:00:15');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (45, 'Test', 125, 2, '2021-12-15 12:37:52', '2021-12-15 14:00:22');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (48, '2020 project status', 2, 175, '2020-11-11 17:18:07', '2021-12-15 14:00:23');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (49, 'add basic files', 0, 9, '2021-08-20 20:55:16', '2021-12-15 14:00:23');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (52, 'Test', 13, 128, '2021-05-02 12:20:07', '2021-12-15 14:00:24');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (53, 'Test', 3, 0, '2021-12-10 11:00:57', '2021-12-15 14:00:25');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (54, 'Merge remote-tracking branch ''origin/master''', 0, 0, '2021-11-25 09:39:11', '2021-12-15 14:00:26');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (55, '#162 resource bundle', 0, 20, '2021-12-08 18:46:52', '2021-12-15 14:00:27');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (56, 'Merge branch ''feature-v2.0.9''', 0, 5, '2021-12-13 12:47:37', '2021-12-15 14:00:27');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (58, 'Update download url', 0, 20, '2021-05-08 06:39:11', '2021-12-15 14:00:28');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (59, 'Update README.md', 0, 0, '2017-05-30 00:32:34', '2021-12-15 14:00:29');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (60, 'Update README.md', 0, 1, '2021-11-11 17:19:12', '2021-12-15 14:00:29');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (61, 'Update README.md', 2, 6, '2019-02-13 22:58:50', '2021-12-15 14:00:30');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (62, 'Update for 0.49.1 release', 2, 155, '2021-12-09 02:55:53', '2021-12-15 14:00:31');

INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (15, 'Eclipse Vagrant Tooling', 2, 2, 'The Eclipse Vagrant Tooling plugin allows users a simple way to manage Vagrant Boxes, and Virtual Machines as one might do with the ''vagrant'' command line tool.
Please, rate if you like my addon', 'https://github.com/sqshq/piggymetrics', 'new.zip', 5, '2021-11-30', 1, 1, 24);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (18, 'Exporter for Eclipse', 2, 20, 'The Yatta Eclipse Exporter is an easy way to save your Eclipse setups and Workspace configurations – for yourself or your team. Export your Eclipse configuration, installed plug-ins, workspace settings and project configuration quickly and easily to a single local file. Share your project setup with your team and just start building great software. Try it now - it''s free!', 'https://github.com/ityouknow/awesome-spring-boot', 'new.zip', 6, '2021-12-06', 0, 1, 27);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (20, 'VsVim', 3, 4, 'This is a Vim Emulation layer for Visual Studio 2015 and above.  It integrates the familiar key binding experience of Vim directly into Visual Studio''s editor.', 'https://github.com/VsVim/VsVim', 'new.zip', 1, '2021-12-10', 0, 0, 29);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (22, 'Winter is Coming', 3, 2, 'A dark theme based on the popular Winter is Coming theme for VS Code. This theme was created in cooperation with the original author of the theme, John Papa. ', 'https://github.com/madskristensen/WinterIsComing', 'new.zip', 21, '2021-12-10', 1, 1, 31);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (23, 'Smart Attach', 3, 4, 'The "Attach debugger" feature of Visual Studio is launched by Ctrl + Alt + P. But it shows all windows processed which are not relevant and it''s hard to find the actual website process that you want to debug.
', 'https://github.com/Geeksltd/VSIX.SmartAttach', 'new.zip', 0, '2021-12-10', 0, 0, 34);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (24, 'GitHub Extension', 3, 20, '
Visual Studio 2022 now includes the functionality from this extension out of the box. We recommend downloading Visual Studio 2022 to get the best experience.', 'https://github.com/github/VisualStudio', 'new.zip', 46, '2021-12-10', 1, 1, 35);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (26, 'SQLite Toolbox', 2, 1, 'The SQL Server Compact & SQLite Toolbox adds several features to help your SQL Server Compact and SQLite development efforts', 'https://github.com/ErikEJ/SqlCeToolbox', 'new.zip', 34, '2021-12-10', 0, 1, 38);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (28, 'ResXManager', 3, 2, 'This tool provides central access to all ResX-based string resources in your solution. You can quickly navigate through all resource files and view the content in a well-arranged data grid. ', 'https://github.com/dotnet/ResXResourceManager', 'new.zip', 12, '2021-12-10', 0, 0, 41);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (30, 'Sirius', 2, 4, 'Sirius is an Eclipse project (http://www.eclipse.org/sirius) that allows you to easily create your own graphical modeling tools. It leverages the Eclipse modeling technologies, including EMF for the model management and GMF for the graphical representation. Based on a viewpoint approach, Sirius makes it possible to equip teams who have to deal with complex architectures on specific domains.', 'https://github.com/dyc87112/SpringBoot-Learning', 'new.zip', 6, '2021-12-10', 0, 1, 43);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (31, 'Rust', 1, 1, 'IntelliJ Rust is an open-source Rust plugin compatible with all IntelliJ-based IDEs. In pair with IntelliJ TOML, it aims at bringing full IDE experience to your workflow with Rust and Cargo. You can find the plugin''s source code in this github repository.', 'https://github.com/intellij-rust/intellij-rust', 'new.zip', 0, '2021-12-10', 0, 1, 44);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (32, 'Kotlin', 1, 20, 'Kotlin has great support and many contributors in its fast-growing global community. Enjoy the benefits of a rich ecosystem with a wide range of community libraries. Help is never far away — consult extensive community resources or ask the Kotlin team directly.', 'https://github.com/JetBrains/kotlin', 'new.zip', 70, '2021-12-10', 0, 1, 45);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (34, 'BashSupport', 1, 4, 'Prompted by changes in the JetBrains default plugin offerings, a slow transition away from BashSupport has been started in July 2019. Since it cannot build on top of the new JetBrains Shell plugin, it requires too much maintenance effort for what it adds. So after 10 years of development, BashSupport has been retired.', 'https://github.com/BashSupport/BashSupport', 'new.zip', 39, '2021-12-10', 0, 1, 48);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (36, 'Material Theme UI', 1, 1, 'Make External Themes as a dynamic extension point.Add more popups showing an overlay: Recent Files, Quick Switchers, Branch Chooser and others', 'https://github.com/mallowigi/material-theme-issues/issues', 'new.zip', 0, '2021-12-10', 1, 1, 49);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (38, 'Lombok', 1, 1, 'A plugin that adds first-class support for Project Lombok
', 'https://github.com/mplushnikov/lombok-intellij-plugin', 'new.zip', 0, '2021-12-10', 0, 1, 52);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (40, 'TSvnPwd', 2, 2, 'Forgot your svn password? This tool can be helpful if you checked the "Save authentication" box at some point in the past, but then forgot the password you entered. The core code of this project is transplanted from http://www.leapbeyond.com/ric/TSvnPD. The same as the original author''s description, the program is only based on a single default environment for programming, and it cannot parse all the allowed configuration syntax in Subversion. And it should be noted that this tool can effectively resolve SVN related information only when the same Windows user account that was used to log in to SVN is used and the authentication is passed.
', 'https://github.com/meiMingle/TSvnPwd-intellij', 'new.zip', 0, '2021-12-10', 0, 1, 53);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (41, 'YY Dev Tools', 1, 1, 'A plugin for improving GWT-related project development efficiency and simplifying some operations, such as code navigation.', 'https://github.com/JohnsonEEE/yy-dev-tools', 'new.zip', 0, '2021-12-10', 0, 1, 54);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (42, 'String Manipulation', 1, 2, 'Manage pull requests and conduct code reviews in your IDE with full source-tree context. Comment on any line, not just the diffs. Use jump-to-definition, your favorite keybindings, and code intelligence with more of your workflow.', 'https://github.com/krasa/StringManipulation', 'new.zip', 0, '2021-12-10', 0, 1, 55);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (43, 'Restful Fast Request', 1, 20, 'Restful Fast Request is a powerful restful api toolkit plugin(http client) help you quickly generate url and params by exist method.Plugin = API debug tool + API manager tool
It has a beautiful interface to compose requests,inspect server responses,store your api request and export api request
Plugin help you debug request just in Intellij frame
support Spring framework (Spring MVC / Spring Boot)', 'https://github.com/kings1990/fast-request', 'new.zip', 12, '2021-12-10', 1, 1, 56);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (44, 'Swimat', 5, 1, 'Swimat is an Xcode extension to format your Swift code. It supports the latest Xcode 11. The Re-Indent on Xcode works the same as with Swimat, but Swimat is more convenient because it doesn’t need to work with code selection.', 'https://github.com/Jintin/Swimat', 'dummy content.zip', 0, '2021-12-10', 0, 1, 58);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (45, 'SwitchIt', 5, 1, 'Manually implementing all enum switch statements is error-prone and time-consuming too. SwitchIt makes it much easier for you.', 'https://github.com/HarmVanRisk/SwitchIt', 'dummy content.zip', 0, '2021-12-10', 0, 1, 59);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (46, 'Nef', 5, 1, 'Nef makes your code snippets much nicer when sharing them with someone or embedding them in your articles.
', 'https://github.com/bow-swift/nef-plugin', 'dummy content.zip', 19, '2021-12-10', 1, 1, 60);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (47, 'Snowonder', 5, 20, 'Snowonder is Xcode Extension that adds some convenient formatting operations for Import Declarations. This extension is created with latest stable Swift release and fully open source. Snowonder is based on official Apple''s XcodeKit which supports only Xcode of versions 8 and higher. If you''re looking for a Snowonder that is compatible with Xcode 7.3 version please check legacy branch.', 'https://github.com/Karetski/Snowonder', 'dummy content.zip', 0, '2021-12-10', 0, 1, 61);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (48, 'SwiftFormat', 5, 1, 'SwiftFormat is a code library and command-line tool for reformatting Swift code on macOS or Linux.
SwiftFormat goes above and beyond what you might expect from a code formatter. In addition to adjusting white space it can insert or remove implicit self, remove redundant parentheses, and correct many other deviations from the standard Swift idioms.', 'https://github.com/nicklockwood/SwiftFormat', 'dummy content.zip', 1, '2021-12-10', 1, 1, 62);


INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (10, 20, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (11, 20, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (12, 20, 5);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (13, 20, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (16, 23, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (17, 24, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (18, 24, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (19, 24, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (20, 24, 10);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (22, 28, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (23, 28, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (24, 28, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (28, 30, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (29, 30, 10);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (30, 30, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (48, 36, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (49, 36, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (50, 36, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (57, 41, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (58, 41, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (59, 41, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (60, 42, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (61, 42, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (62, 42, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (63, 42, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (64, 42, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (69, 44, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (70, 44, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (71, 44, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (72, 44, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (73, 44, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (74, 45, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (75, 45, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (76, 45, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (77, 45, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (78, 45, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (79, 46, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (80, 46, 13);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (81, 46, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (82, 47, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (83, 47, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (84, 47, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (85, 47, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (86, 47, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (87, 47, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (89, 48, 20);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (90, 48, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (91, 34, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (92, 34, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (93, 34, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (94, 26, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (95, 26, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (96, 26, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (97, 40, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (98, 40, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (99, 40, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (115, 18, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (116, 18, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (117, 18, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (118, 18, 13);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (119, 18, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (120, 18, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (121, 18, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (122, 31, 7);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (123, 31, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (124, 31, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (125, 38, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (126, 38, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (127, 38, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (131, 43, 7);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (132, 43, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (133, 43, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (143, 32, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (144, 32, 5);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (145, 32, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (192, 22, 7);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (193, 22, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (201, 15, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (202, 15, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (203, 15, 13);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (204, 15, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (205, 15, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (206, 15, 19);


INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (1, 1, 32, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (2, 1, 20, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (3, 1, 22, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (4, 1, 18, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (5, 1, 15, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (6, 1, 46, 4);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (7, 1, 43, 3);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (8, 2, 15, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (9, 2, 43, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (10, 1, 24, 3);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (11, 1, 34, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (12, 1, 26, 4);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (13, 1, 30, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (14, 1, 31, 1);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (15, 1, 40, 2);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (16, 1, 48, 3);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (17, 1, 38, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (18, 2, 24, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (19, 4, 22, 1);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (20, 4, 24, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (21, 4, 20, 5);
INSERT INTO addonis.ratings (id, user_id, addon_id, rating) VALUES (22, 1, 36, 2);
