package org.example.DAO;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.connection.ConnectionEnabler;
import org.example.model.Orders;

/**
 * Specific order queries
 */
public class OrdersDAO  extends AbstractDAO<Orders>{

}
