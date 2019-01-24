# Hibernate with Spring

Client: HPE, Bangalore 

Duration: 5 days

From: 25th Jan 2019

To: 31st Jan 2019

### Download h2 database from here:

<a href="http://www.h2database.com/h2-2018-03-18.zip">h2 database</a>

### Download the database script from here:

<a href="http://vinod.co/resources/dbscript.zip">DB Script (zip file)</a>

### Command to import tables and data from the script file in h2 database:

```sql
runscript from 'ABSOULTE_PATH_TO_THE_DBSCRIPT.SQL_FILE'
```

### Download libraries (JAR files) for Hibernate and Spring framework from here:

<a href="https://vinod.co/resources/hibernate-spring-jars.zip">Hibernate and Spring JARs</a> (Download this only if you do not want to use Maven)


### Hibernate dependency (Maven)

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>4.3.11.Final</version>
</dependency>
```

### Sample hibernate.cfg.xml

```xml
<?xml version='1.0' encoding='utf-8'?>

<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
	<session-factory>
		<property name="hibernate.connection.driver_class">org.h2.Driver</property>
		<property name="hibernate.connection.url">jdbc:h2:tcp://localhost/~/2019_01_HIBERNATE_SPRING_HPE</property>
		<property name="hibernate.connection.username">sa</property>
		<property name="hibernate.connection.password"></property>

		<property name="show_sql">true</property>
		<property name="format_sql">true</property>
		<property name="dialect">org.hibernate.dialect.H2Dialect</property>
	</session-factory>
</hibernate-configuration>

```

<!--

### Sample context.xml

```xml
<?xml version="1.0"?>

<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd">

	<bean name="" class="" >
	</bean>

</beans>
```
-->