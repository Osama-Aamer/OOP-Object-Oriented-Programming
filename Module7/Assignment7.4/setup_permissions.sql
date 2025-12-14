
SHOW GRANTS FOR 'appuser'@'localhost';
-- Verify privileges

FLUSH PRIVILEGES;
-- Apply the privilege changes

GRANT CREATE, DROP, ALTER ON currency_converter.* TO 'appuser'@'localhost';
-- Grant CREATE, DROP, ALTER permissions to appuser (needed for JPA schema generation)


