
INSERT INTO addonis.roles (id, role_name) VALUES (1, 'Admin');
INSERT INTO addonis.roles (id, role_name) VALUES (2, 'User');

INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (1, 'IntellijIDE', 'intellij.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (2, 'Eclipse', 'eclipse.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (3, 'Visual Studio', 'studio.png');
INSERT INTO addonis.target_ide (id, name, image_file_name) VALUES (4, 'PyCharm', 'pycharm.png');

INSERT INTO addonis.tags (id, name) VALUES (17, 'angular');
INSERT INTO addonis.tags (id, name) VALUES (9, 'branch');
INSERT INTO addonis.tags (id, name) VALUES (1, 'Code');
INSERT INTO addonis.tags (id, name) VALUES (3, 'Coding');
INSERT INTO addonis.tags (id, name) VALUES (12, 'commit');
INSERT INTO addonis.tags (id, name) VALUES (7, 'dark');
INSERT INTO addonis.tags (id, name) VALUES (2, 'Databases');
INSERT INTO addonis.tags (id, name) VALUES (18, 'Editor');
INSERT INTO addonis.tags (id, name) VALUES (4, 'fsharp');
INSERT INTO addonis.tags (id, name) VALUES (10, 'git');
INSERT INTO addonis.tags (id, name) VALUES (11, 'Open Source');
INSERT INTO addonis.tags (id, name) VALUES (13, 'power tool');
INSERT INTO addonis.tags (id, name) VALUES (15, 'Productivity');
INSERT INTO addonis.tags (id, name) VALUES (19, 'resource');
INSERT INTO addonis.tags (id, name) VALUES (16, 'Script');
INSERT INTO addonis.tags (id, name) VALUES (8, 'theme');
INSERT INTO addonis.tags (id, name) VALUES (14, 'validation');
INSERT INTO addonis.tags (id, name) VALUES (5, 'vim');
INSERT INTO addonis.tags (id, name) VALUES (6, 'vsvim');

INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (1, 'admin', 'pass', 'Admin', 'Adminov', 'admin@gmail.com', 'dogecoin.jpeg-900x510-big.jpg', 0, 1, '0898758267', 0);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (4, 'gabi', 'Gab123^&&', 'Gabriela', 'Georgieva', 'gabigeorgieva@abv.bg', '254167383_933997764182280_5456277152055192074_n.jpg', 0, 2, '0898708810', 0);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (16, 'gabkk', 'Gab123^&&', 'gabiii', 'ivanova', 'georgievagala@gmail.com', '254167383_933997764182280_5456277152055192074_n.jpg', 0, 2, '0898768010', 0);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (18, 'A.dragnev', 'gg', 'gg', 'gg', 'georgievagabriela507@gmail.com', '262418678_608974720336709_1359589292855243744_n.jpg', 0, 2, '0898702210', 0);
INSERT INTO addonis.users (id, username, password, first_name, last_name, email, picture_url, blocked, role_id, phone_number, enabled) VALUES (19, 'Gabigeorgieva123', 'ee', 'ee', 'ee', 'vasIvanov@abv.bg', '262012826_3092574877677313_6462097667890828242_n.jpg', 0, 2, '0898708856', 0);
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (23, 'Merge pull request #13 from HelloWorld521/dependabot/maven/seckill/com.fasterxml.jackson.core-jackson-databind-2.9.10.1

Bump jackson-databind from 2.5.4 to 2.9.10.1 in /seckill', 32, 12, '2019-11-13 00:00:00', '2021-11-30 18:28:21');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (24, 'Update README.md', 6, 7, '2021-11-16 01:36:16', '2021-12-10 16:15:01');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (25, 'Add desig file', 0, 8, '2021-08-31 09:11:14', '2021-12-04 00:35:04');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (26, 'Add desig file', 0, 8, '2021-08-31 09:11:14', '2021-12-06 20:40:06');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (27, 'm', 1, 1, '2021-03-04 03:07:27', '2021-12-10 16:15:02');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (28, 'Add desig file', 0, 8, '2021-08-31 09:11:14', '2021-12-06 20:46:30');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (29, 'Add base directory to search path for csx/csxe (#2945)', 4, 209, '2021-12-06 18:19:53', '2021-12-10 16:10:04');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (30, 'Various fixes to theme tokens.
Fixes #3', 0, 6, '2021-09-22 18:09:00', '2021-12-10 13:34:10');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (31, 'Various fixes to theme tokens.
Fixes #3', 0, 6, '2021-09-22 18:09:00', '2021-12-10 16:10:04');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (32, 'Adding more exclusion', 1, 0, '2019-05-24 13:11:49', '2021-12-10 13:38:33');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (33, 'Adding more exclusion', 1, 0, '2019-05-24 13:11:49', '2021-12-10 13:38:33');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (34, 'Adding more exclusion', 1, 0, '2019-05-24 13:11:49', '2021-12-10 16:10:05');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (35, 'Merge pull request #2642 from Taysser-Gherfal/VS_Marketplace_Page_Updates

Updating VS Market place content to support VS2022 launch', 15, 378, '2021-10-28 18:39:56', '2021-12-10 16:10:06');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (36, 'Merge pull request #2642 from Taysser-Gherfal/VS_Marketplace_Page_Updates

Updating VS Market place content to support VS2022 launch', 15, 378, '2021-10-28 18:39:56', '2021-12-10 13:44:07');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (37, 'Enable debug', 0, 7, '2021-11-07 15:29:51', '2021-12-10 13:52:08');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (38, 'Enable debug', 0, 6, '2021-11-07 15:29:51', '2021-12-10 16:10:07');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (39, 'V 1.62', 3, 51, '2021-11-21 13:23:10', '2021-12-10 13:58:03');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (40, 'V 1.62', 3, 51, '2021-11-21 13:23:10', '2021-12-10 13:58:40');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (41, 'V 1.62', 3, 51, '2021-11-21 13:23:10', '2021-12-10 16:10:08');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (42, '????', 19, 31, '2021-11-09 09:11:29', '2021-12-10 14:46:01');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (43, '????', 19, 31, '2021-11-09 09:11:29', '2021-12-10 16:10:09');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (44, 'Merge #8045

8045: Fix auto-import in `include!`-ed files r=Undin a=dima74

Fixes #8037

changelog: Fix auto-import in `include!`-ed files


Co-authored-by: Dmitry Murzin <diralik@yandex.ru>', 100, 1220, '2021-12-09 21:01:56', '2021-12-10 16:10:14');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (45, 'Revert "[FE 1.0] Provide an API to clean compile time initializer cache for variable descriptor"

This reverts commit 0e6762acc35bdd383b34edc126967f62e537df95.', 126, 2, '2021-12-10 15:54:55', '2021-12-10 16:10:20');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (46, 'Improve performance of classloaders + small refactoring

During the initialization of the classloader, we preload some jar files, that are necessary for the compiler to work correctly. In this process of preloading classes, we unpack each jar file and look at the value of the Class-Path in its META_INF. So we will determine, which files are depends on by original jars. We will load the resulting jars in the next iteration of class preloading. Thus, we get nested classloaders. However, in practice, it turned out that the jar files needed for the second iteration were already loaded in the first iteration, so there is no point in loading them again. Moreover, if we do not find the class in the first loader, then we will not find it in the second either. However, the case, when there are some jars from Class-Path of original jars and they were not loaded by first iteration, does not change.
Aldo needed for KT-49786', 126, 2, '2021-12-10 14:13:02', '2021-12-10 15:37:38');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (47, '2020 project status', 2, 175, '2020-11-11 17:18:07', '2021-12-10 15:39:31');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (48, '2020 project status', 2, 175, '2020-11-11 17:18:07', '2021-12-10 16:10:21');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (49, 'add basic files', 0, 9, '2021-08-20 20:55:16', '2021-12-10 16:10:22');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (50, 'add basic files', 0, 9, '2021-08-20 20:55:16', '2021-12-10 15:42:12');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (51, 'Bump org.jetbrains.intellij from 0.6.5 to 0.7.3 (#1033)

Bumps org.jetbrains.intellij from 0.6.5 to 0.7.3.

Signed-off-by: dependabot-preview[bot] <support@dependabot.com>

Co-authored-by: dependabot-preview[bot] <27856297+dependabot-preview[bot]@users.noreply.github.com>', 12, 128, '2021-05-02 12:20:07', '2021-12-10 15:48:20');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (52, 'Bump org.jetbrains.intellij from 0.6.5 to 0.7.3 (#1033)

Bumps org.jetbrains.intellij from 0.6.5 to 0.7.3.

Signed-off-by: dependabot-preview[bot] <support@dependabot.com>

Co-authored-by: dependabot-preview[bot] <27856297+dependabot-preview[bot]@users.noreply.github.com>', 12, 128, '2021-05-02 12:20:07', '2021-12-10 16:10:23');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (53, 'Merge pull request #17

Changelog update - `v0.0.8`', 2, 0, '2021-12-10 11:00:57', '2021-12-10 16:10:23');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (54, 'Merge remote-tracking branch ''origin/master''', 0, 0, '2021-11-25 09:39:11', '2021-12-10 16:10:24');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (55, '#162 resource bundle', 0, 20, '2021-12-08 18:46:52', '2021-12-10 16:10:24');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (56, 'md', 0, 3, '2021-12-09 12:38:12', '2021-12-10 16:10:25');
INSERT INTO addonis.repo_info (id, last_commit_title, open_pull_requests, open_issues, last_commit_date, last_refresh) VALUES (57, 'md', 0, 3, '2021-12-09 12:38:12', '2021-12-10 16:00:40');




INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (15, 'Eclipse Vagrant Tooling', 2, 1, 'The Eclipse Vagrant Tooling plugin allows users a simple way to manage Vagrant Boxes, and Virtual Machines as one might do with the ''vagrant'' commandline tool.', 'https://github.com/sqshq/piggymetrics', 'new.zip', 3, '2021-11-30', 1, 0, 24);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (18, 'Exporter for Eclipse', 2, 1, 'The Yatta Eclipse Exporter is an easy way to save your Eclipse setups and Workspace configurations – for yourself or your team. Export your Eclipse configuration, installed plug-ins, workspace settings and project configuration quickly and easily to a single local file. Share your project setup with your team and just start building great software. Try it now - it''s free!', 'https://github.com/ityouknow/awesome-spring-boot', 'new.zip', 2, '2021-12-06', 0, 0, 27);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (20, 'VsVim', 3, 1, 'This is a Vim Emulation layer for Visual Studio 2015 and above.  It integrates the familiar key binding experience of Vim directly into Visual Studio''s editor.', 'https://github.com/VsVim/VsVim', 'new.zip', 1, '2021-12-10', 0, 0, 29);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (22, 'Winter is Coming', 3, 1, 'A dark theme based on the popular Winter is Coming theme for VS Code. This theme was created in cooperation with the original author of the theme, John Papa.', 'https://github.com/madskristensen/WinterIsComing', 'new.zip', 0, '2021-12-10', 0, 0, 31);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (23, 'Smart Attach', 3, 1, 'The "Attach debugger" feature of Visual Studio is launched by Ctrl + Alt + P. But it shows all windows processed which are not relevant and it''s hard to find the actual website process that you want to debug.
', 'https://github.com/Geeksltd/VSIX.SmartAttach', 'new.zip', 0, '2021-12-10', 0, 0, 34);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (24, 'GitHub Extension', 3, 1, '
Visual Studio 2022 now includes the functionality from this extension out of the box. We recommend downloading Visual Studio 2022 to get the best experience.', 'https://github.com/github/VisualStudio', 'new.zip', 0, '2021-12-10', 0, 0, 35);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (26, 'SQLite and SQL Server Compact', 3, 1, 'The SQL Server Compact & SQLite Toolbox adds several features to help your SQL Server Compact and SQLite development efforts', 'https://github.com/ErikEJ/SqlCeToolbox', 'new.zip', 0, '2021-12-10', 0, 0, 38);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (28, 'ResXManager', 3, 1, 'This tool provides central access to all ResX-based string resources in your solution. You can quickly navigate through all resource files and view the content in a well-arranged data grid. ', 'https://github.com/dotnet/ResXResourceManager', 'new.zip', 0, '2021-12-10', 0, 0, 41);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (30, 'Sirius', 2, 1, 'Sirius is an Eclipse project (http://www.eclipse.org/sirius) that allows you to easily create your own graphical modeling tools. It leverages the Eclipse modeling technologies, including EMF for the model management and GMF for the graphical representation. Based on a viewpoint approach, Sirius makes it possible to equip teams who have to deal with complex architectures on specific domains.', 'https://github.com/dyc87112/SpringBoot-Learning', 'new.zip', 0, '2021-12-10', 0, 0, 43);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (31, 'Rust', 1, 1, 'IntelliJ Rust is an open-source Rust plugin compatible with all IntelliJ-based IDEs. In pair with IntelliJ TOML, it aims at bringing full IDE experience to your workflow with Rust and Cargo. You can find the plugin''s source code in this github repository.', 'https://github.com/intellij-rust/intellij-rust/issues', 'new.zip', 0, '2021-12-10', 0, 0, 44);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (32, 'Kotlin', 1, 1, 'Kotlin has great support and many contributors in its fast-growing global community. Enjoy the benefits of a rich ecosystem with a wide range of community libraries. Help is never far away — consult extensive community resources or ask the Kotlin team directly.', 'https://github.com/JetBrains/kotlin', 'new.zip', 0, '2021-12-10', 0, 0, 45);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (34, 'BashSupport', 1, 1, 'Prompted by changes in the JetBrains default plugin offerings, a slow transition away from BashSupport has been started in July 2019. Since it cannot build on top of the new JetBrains Shell plugin, it requires too much maintenance effort for what it adds. So after 10 years of development, BashSupport has been retired.', 'https://github.com/BashSupport/BashSupport/issues', 'new.zip', 0, '2021-12-10', 0, 0, 48);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (36, 'Material Theme UI', 1, 1, 'Make External Themes as a dynamic extension point.Add more popups showing an overlay: Recent Files, Quick Switchers, Branch Chooser and others', 'https://github.com/mallowigi/material-theme-issues/issues', 'new.zip', 0, '2021-12-10', 0, 0, 49);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (38, 'Lombok', 1, 1, 'A plugin that adds first-class support for Project Lombok
', 'https://github.com/mplushnikov/lombok-intellij-plugin/issues', 'new.zip', 0, '2021-12-10', 0, 0, 52);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (40, 'TSvnPwd', 1, 1, 'Forgot your svn password? This tool can be helpful if you checked the "Save authentication" box at some point in the past, but then forgot the password you entered. The core code of this project is transplanted from http://www.leapbeyond.com/ric/TSvnPD. The same as the original author''s description, the program is only based on a single default environment for programming, and it cannot parse all the allowed configuration syntax in Subversion. And it should be noted that this tool can effectively resolve SVN related information only when the same Windows user account that was used to log in to SVN is used and the authentication is passed.
', 'https://github.com/meiMingle/TSvnPwd-intellij/issues', 'new.zip', 0, '2021-12-10', 0, 0, 53);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (41, 'YY Dev Tools', 1, 1, 'A plugin for improving GWT-related project development efficiency and simplifying some operations, such as code navigation.', 'https://github.com/JohnsonEEE/yy-dev-tools', 'new.zip', 0, '2021-12-10', 0, 0, 54);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (42, 'String Manipulation', 1, 1, 'Manage pull requests and conduct code reviews in your IDE with full source-tree context. Comment on any line, not just the diffs. Use jump-to-definition, your favorite keybindings, and code intelligence with more of your workflow.', 'https://github.com/krasa/StringManipulation', 'new.zip', 0, '2021-12-10', 0, 0, 55);
INSERT INTO addonis.addons (id, name, target_ide_id, creator_id, description, origin_url, binary_content_url, number_of_downloads, creation_date, featured, approved, repo_info_id) VALUES (43, 'Restful Fast Request', 1, 1, 'Restful Fast Request is a powerful restful api toolkit plugin(http client) help you quickly generate url and params by exist method.Plugin = API debug tool + API manager tool
It has a beautiful interface to compose requests,inspect server responses,store your api request and export api request
Plugin help you debug request just in Intellij frame
support Spring framework (Spring MVC / Spring Boot)', 'https://github.com/kings1990/fast-request/issues', 'new.zip', 0, '2021-12-10', 0, 0, 56);
# rating

INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (10, 20, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (11, 20, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (12, 20, 5);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (13, 20, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (14, 22, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (15, 22, 7);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (16, 23, 3);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (17, 24, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (18, 24, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (19, 24, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (20, 24, 10);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (21, 26, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (22, 28, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (23, 28, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (24, 28, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (28, 30, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (29, 30, 10);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (30, 30, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (31, 15, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (32, 15, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (33, 15, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (34, 15, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (35, 18, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (36, 18, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (37, 18, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (38, 18, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (39, 31, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (40, 31, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (41, 31, 7);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (42, 32, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (43, 32, 5);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (44, 32, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (45, 34, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (46, 34, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (47, 34, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (48, 36, 12);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (49, 36, 16);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (50, 36, 2);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (51, 38, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (52, 38, 14);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (53, 38, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (54, 40, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (55, 40, 15);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (56, 40, 18);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (57, 41, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (58, 41, 9);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (59, 41, 11);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (60, 42, 8);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (61, 42, 4);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (62, 42, 19);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (63, 42, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (64, 42, 1);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (65, 43, 17);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (66, 43, 6);
INSERT INTO addonis.addons_tags (id, addon_id, tag_id) VALUES (67, 43, 7);

