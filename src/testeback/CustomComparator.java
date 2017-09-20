/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeback;

import java.util.Comparator;

/**
 *
 * @author cil.6000001126
 */
public class CustomComparator implements Comparator<tbCustomerAccountWrapper> {
    
        @Override
        public int compare(tbCustomerAccountWrapper tb1, tbCustomerAccountWrapper tb2) {
            return tb1.compareTo(tb2.value);
        }
    }