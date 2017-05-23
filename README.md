# Rightplace Project DWWS-2017
by Flávio Machado and Mateus Tassinari

How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this tutorial at JButler's wiki.

Install Eclipse Neon (version 4.6);

Install WildFly 10 and create a Server configuration within Eclipse;

Install MySQL and create a schema called rightplace and a user called rightplace with password rightplace and full access to the homonymous database;

Configure the MySQL JDBC driver in WildFly;

Configure the datasource in WildFly's standalone.xml file:

 <datasource jta="true" jndi-name="java:jboss/datasources/Rightplace" pool-name="RightplacePool" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/rightplace</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>rightplace</user-name>
                        <password>rightplace</password>
                    </security>
                </datasource>
                <driver name="mysql" module="com.mysql">
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                </driver>

In Eclipse, use File > Import > Git > Projects from Git to import the Eclipse project existing in this repository;

You might have to adjust the server settings in the imported project: right-click the Rightplace project and select Properties. In the Server section, select the WildFly 10.x server. In the Targeted Runtimes section, select the WildFly 10.x Runtime;
