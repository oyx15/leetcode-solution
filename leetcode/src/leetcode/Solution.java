package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.swing.tree.TreeNode;


public class Solution {
	
	public static int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
		for(int i = 0; i < numbers.length-1; i++){
			for(int j = i + 1; j < numbers.length-1; j++){
				if(numbers[i]+numbers[j] == target){
						result[0] = i;
						result[1] = j;	
				}
			}
		}
		return result;
    }

	public int[] twoSum1(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
	 
		for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				int index = map.get(numbers[i]);
				result[0] = index+1 ;
				result[1] = i+1;
				break;
			} else {
				map.put(target - numbers[i], i);
			}
		}
	 
		return result;
	    }
	
	// Definition for singly-linked list.
	  public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	     }
	  }
	 
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int sum = 0;
//		int carry = 0;
		ListNode result = new ListNode(0);
		ListNode p1 = l1, p2 = l2, p3 = result;
		while(p1 != null || p2 != null){
			if(p1 != null){
				sum += p1.val;
				p1 = p1.next;
			}
			if(p2 != null){
				sum += p2.val;
				p2 = p2.next;
			}
			p3.next = new ListNode(sum % 10);
			p3 = p3.next;
			sum /= 10;
		}
		if(sum == 1){
			p3.next = new ListNode(1);
		}
		return result.next;
        
    }

	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		double median;
		if((m+n)%2 != 0)
			median = findKth(A, B, (m+n)/2, 0, m-1, 0, n-1);
		else 
			median = (findKth(A, B, (m+n)/2, 0, m-1, 0, n-1) + findKth(A, B, (m+n)/2-1, 0, m-1, 0, n-1)) / 2;
		return median;
    }
	
	public static double findKth(int A[] , int B[] , int k, int aStart, int aEnd, int bStart, int bEnd) {
		// TODO Auto-generated method stub
		int aLen = aEnd-aStart+1;
		int bLen = bEnd-bStart+1;
		
		if(aLen == 0) return B[bStart + k];
		if(bLen == 0) return A[aStart + k];
		if(k == 0) return A[aStart] > B[bStart] ? B[bStart]:A[aStart];
		
		int aMid = aLen * k / (aLen + bLen);
		int bMid = k - aMid - 1;
		
		aMid = aMid + aStart;
		bMid = bMid + bStart;
		
		if(A[aMid] > B[bMid]){
			k = k -(bMid - bStart + 1);
			bStart = bMid + 1;
			aEnd = aMid;			
		}else{
			k = k -(aMid - aStart + 1);	
			aStart = aMid + 1;
			bEnd = bMid;
		}
		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}


	public static String convert (String s, int nRows){
		//if(s.length() == 0) return null;
		if(nRows<=1 || s.length()<=1) return s;
		String result;
		boolean up = false;
		int col=0;
		StringBuilder[] str = new StringBuilder[nRows];
		for(int j=0; j<nRows; j++){
			str[j] = new StringBuilder(); 
		}
		for(int i=0; i<s.length(); i++){	
			str[col].append(s.charAt(i));
			if(col==0) up=false;
			if(col == (nRows-1)) up = true;
			if(!up) col++;
			else col--;
		}
		result = str[0].toString();
		for(int j=1; j<nRows; j++){
			result = result + str[j].toString();
		}
		return result;
	}
	
	public static  String longestPalindrome1(String s) {
		int length=s.length();
		int maxlen = 0;
		int end = 0;
		int[][] dynamic = new int[length][length];
		String str = new StringBuffer(s).reverse().toString();
		if(length==0 || s==str) return s;
		for(int i=0; i<length; i++){
			for(int j=0; j<length; j++){
				int n = (i-1>=0 && j-1>=0)? dynamic[i-1][j-1]:0;
				dynamic[i][j] = s.charAt(i)==str.charAt(j)? 1+n:0;
				if(dynamic[i][j]>maxlen){
					maxlen = dynamic[i][j];
					end = i;
				}
			}
		}
		return s.substring(end-maxlen+1, end+1); 
    }
	
	public static boolean isPalindrome(String s, int startIndex, int endIndex){
		for(int i = startIndex, j = endIndex; i <= j; i++,j--)
			if(s.charAt(i) != s.charAt(j)) return false;
		return true;
	}
	
	public static  String longestPalindrome2(String s) {
		int length=s.length();
		int longestLen = 0;
		int longestIndex = 0;
		for(int currentIndex = 0; currentIndex < length; currentIndex++){
			if(isPalindrome(s, currentIndex - longestLen, currentIndex)){
				longestLen += 1;
				longestIndex = currentIndex;
			}else if(currentIndex - longestLen -1 >= 0 && isPalindrome(s, currentIndex - longestLen -1, currentIndex)){
				longestLen += 2;
				longestIndex = currentIndex;
			} 
		}
		longestIndex++;
		return s.substring(longestIndex - longestLen, longestIndex); 
    }
	
	public static  String longestPalindromePre(String s) {
		int length = s.length();
		if(length == 0) return "^$";
		String sign  = "^";
		for(int i = 0; i < length; i++)
			sign = sign + "#" + s.charAt(i);
		sign += "#$";
		return sign;
		
	}
	
	public static  String longestPalindrome3(String s) {
		String str = longestPalindromePre(s);
		int length = str.length();
		int center = 0, right = 0;
		int[] counter = new int[length];
		for(int i = 1; i < length - 1; i++){
			int mirror  = 2 * center - i;
			counter[i] = (right > i)? Math.min(right-i, counter[mirror]):0;
			while(str.charAt(i - counter[i] - 1) == str.charAt(i + counter[i] + 1)) counter[i]++;
			if(counter[i] + i > right){
				center = i;
				right = center + counter[i];			
			}	
		}
		
		int longestLen = 0;
		int index = 0;
		for(int i = 1; i < length - 1; i++){
			if(counter[i] > longestLen){
				longestLen = counter[i];
				index = i;
			}
		}
		int startIndex = (index - longestLen - 1) / 2;
		return s.substring(startIndex, startIndex + longestLen);
		
	}
	
	public static int reverse(int x) {
		double result;
        String s = String.valueOf(x);
        boolean negative = false;
        if(x<0) {
        	s = s.substring(1, s.length());
        	negative = true;
        	}
        s = new StringBuffer(s).reverse().toString();
        result = Double.parseDouble(s);
        if(negative){
        	result = - result;
        }
        if(result > 2147483647 || result < -2147483648){
        	return 0;
        }
		return (int)result;                  
    }

	public boolean isPalindrome(int x) {
		//determine an integer is palindrome or not, eg. 12321
		//what if the reverse number if overflow?
		if(x < 0) return false;
		int div = 1;
		while(x/div >= 10) div *= 10;
		while(x !=0){
			if(x%10 != x/div) return false;
			x = (x % div) / 10;
			div /= 100;
		}
		return true;        
    }

	public static int atoi(String str) { 
		//convert a string to an integer
		//consider all kinds of circumstances of a string
		double result = 0;
		char flag = 0;
		int i = 0;
		boolean intFlag = true;
		while(str.length()>=0 && str.startsWith(" ")){
			str = str.substring(1, str.length());
		}
		if(str.length() == 0) return 0;
		if(str.startsWith("-")) {
			flag = '-';
			i++;
		}
		else if(str.startsWith("+")) {
			flag = '+';
			i++;
		}
		while(i < str.length()){
			if(intFlag && str.charAt(i) <= '9' && str.charAt(i) >= '0'){
				result = result * 10 + (str.charAt(i) - '0');
			}
			if(!(str.charAt(i) <= '9' && str.charAt(i) >= '0')){
				intFlag = false;
			}	
			i++;
		}
		if(flag == '-' ) result = -result;
		if(result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
		else if(result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
		return (int) result;
    }
	
	public String largestNumber(int[] num) {
		int n = num.length;
		StringBuilder largest = new StringBuilder();
		for(int i = 1; i < n; i++){
			
		}
		
		return largest.toString();
        
    }

	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		int m = matrix.length;
		
	    if(matrix == null || m == 0) 
	    	return result;
	    int n = matrix[0].length;
	    int x =0;
    	int y = 0;
	    while(m > 0 && n > 0){
	    	if(m == 1){
		    	for(int i =0; i< n; i++)
		    		result.add(matrix[x][y++]);
		    	break;
		    }
	    	else if(n == 1){
		    	for(int i =0; i< m; i++)
		    		result.add(matrix[x++][y]);
		    	break;
		    }
		    for(int i=0; i<n-1;i++)
		    	result.add(matrix[x][y++]);
		    for(int j=0; j<m-1;j++)
		    	result.add(matrix[x++][y]);
		    for(int i=0; i<n-1;i++)
		    	result.add(matrix[x][y--]);
		    for(int j=0; j<m-1;j++)
		    	result.add(matrix[x--][y]);
		    x++;
		    y++;
		    m = m-2;
		    n = n-2;
	    }
	   
		return result;
        
    }

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if( l1 == null && l2 == null) return null;
		if(l1 == null && l2 != null) return l2;
		if(l2 == null && l1 != null) return l1;

		ListNode result = new ListNode(0);
		ListNode node = result;
		int n1, n2;
		while(l1 != null && l2 != null){
			n1 = l1.val;
			n2 = l2.val;
			if(n1 > n2){
				node.next = l2;
				l2 = l2.next;
			}
			else{
				node.next = l1;
				l1 = l1.next;
			}
			node = node.next;	
		}
		if( l1 != null)
			node.next = l1;
		if( l2 != null)
			node.next = l2;
		return result.next;
        
    }

	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		//ListNode result = new ListNode(0);
		ListNode node = new ListNode(0);
		if(l1.val > l2.val){
			node.next = l2;
			node.next.next = mergeTwoLists(l1,l2.next);
		}else{
			node.next = l1;
			node.next.next = mergeTwoLists(l1.next,l2);
		}
		//node = node.next;
		return node.next;
		
	}
	
	public void merge(int A[], int m, int B[], int n) {
		while(m > 0 && n > 0){
			if(A[m-1] > B[n-1]){
				A[m+n-1] = A[m-1];
				m--;
			}else{
				A[m+n-1] = B[n-1];
				n--;
			}
			
		}
		while(n > 0){
			A[m+n-1] = B[n-1];
			n--;
		}   
    }

	/**
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
//	public List<Integer> inorderTraversal(TreeNode root) {
//		List<Integer> inorderResult = new ArrayList<Integer>();
//		if(root == null) 
//		    return inorderResult;
//		Stack<TreeNode> stack  = new Stack<TreeNode>();
//		TreeNode node = root;
//		while(node != null || !stack.empty()){
//			if(node != null){
//				stack.push(node);
//				node = node.left;
//			}else{
//				TreeNode tnode = stack.pop();
//				
//				inorderResult.add(tnode.val);
//				node = tnode.right;
//			}
//		}
//		return inorderResult;
//        
//    }
	
//	public static String inorder(String postorder){
//		if(postorder == null) return postorder;
//		while(postorder){
//			
//		}
//		return null;
//	}
	
	public static boolean isUnique(String str){
		int size = str.length();
		HashMap<String, Boolean> myMap = new HashMap<String, Boolean>();
		for(int i = 0; i< size; i++){
			char x = str.charAt(i);
			Boolean iter = myMap.get(x);
			if(iter != null){
				boolean flag = true;
				myMap.put(String.valueOf(x), flag);		
			}else return false;
		}
		return true;
	}
	
	public static boolean printTree(String str){
		//Queue<Integer> myque = new Queue<Integer>();
		
		int size = str.length();
		HashMap<String, Boolean> myMap = new HashMap<String, Boolean>();
		for(int i = 0; i< size; i++){
			char x = str.charAt(i);
			Boolean iter = myMap.get(x);
			if(iter != null){
				boolean flag = true;
				myMap.put(String.valueOf(x), flag);		
			}else return false;
		}
		return true;
	}
	
	public static int romanToInt(String s) {
		if (s == null) return 0;
        int result = 0;
        if(s.length() < 2) return charToInt(s.charAt(0));
        int before = 0;
        for(int i = 0; i < s.length(); i++){
        	int a = charToInt(s.charAt(i));
        	//System.out.println(a);
        	result += a;
        	if(i > 0 && a > before)
        		//be careful about what to be substracted
        		result -= 2*before; 
        	before = a;
        	//System.out.println(result);
        }
        return result;
    }
    
    public static int charToInt(char c){
        switch(c){
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
        }
    }
    
    public static int romanToInt1(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int[] a = {1000,500,100,50,10,5,1};
        char[] r = {'M','D','C','L','X','V','I'};
        int ret = 0;
        int last = 0;
         
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < r.length; j++){
                if(r[j] == s.charAt(i)){
                    ret = ret + a[j];
                    if(last > j){
                        ret = ret - 2*a[last];
                    }
                    last = j;
                    System.out.println(last);
                }
            }
        }
        return ret;
    }
    
    public static String intToRoman(int num) {
        String result ="";
        char[] roman = {'I','V','X','L','C','D','M'};
        //int[] rtoi = {1,5,10,50,100,500,1000};
        int scale = 1000;
        for(int i = 6; i >= 0 ;i = i-2){
        	int a = num/scale; 
        	//System.out.println(a);
        	if(a != 0){
        		if(a <= 3){
        			while(a > 0){
            			result += roman[i];
            			a--;
            		}
        		}
        		else if(a == 4){
        			result += roman[i];
        			result += roman[i+1];
        		}
        		else if(a == 5)
        			result += roman[i+1];
        		else if(a > 5 && a <= 8){
        			result += roman[i+1];
        			while(a > 5){
            			result += roman[i];
            			a--;
            		}
        		}
        		else if(a == 9){
        			result += roman[i];
        			result += roman[i+2];
        		}
        			
        	}
        	num = num % scale;
        	scale = scale / 10;
        }
        return result;
        
    }
    
    public static String intToRoman2(int num) {
        String result ="";
        String[] roman = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        int[] rtoi =     {1,    4,  5,   9,  10, 40,  50, 90, 100, 400,500,900,1000};
        int index = roman.length - 1;
        while(num > 0 && index >= 0){
        	if(num >= rtoi[index]){
            	result += roman[index];
            	num -= rtoi[index];
            }else index--;
        }
        return result;        
    }
    
    
    public static String longestCommonPrefix(String[] strs) {
    	int len = strs.length;
    	if(len == 0) return "";
    	String prefix = strs[0];
    	for(int i = 0 ; i < len; i++){
    		int longest = prefix.length();
    	    String common = "";
    	    int len0 = strs[i].length();
		    longest = longest > len0 ? len0 : longest;
		    for(int j = 0; j< longest; j++){
		    	if(strs[i].charAt(j) == prefix.charAt(j))
		    		common += prefix.charAt(j);else break;     
		    }
		    prefix = common;
    	}
		return prefix;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node1 = head;
        ListNode node2 = head;
        for(int i = 0; i < n; i++)
            node1 = node1.next;
        if(node1 == null)return node2.next; //be careful about use . or ->
        while(node1.next != null){
            node1 = node1.next;
            node2 = node2.next;
        }
        node2.next = node2.next.next;
        return head;
    }

    public static boolean isValid(String s) {
        int size = s.length();
        if(size%2 != 0)return false;
        Stack<String> parentheses = new Stack<String>();
        int i = 0;
        while(i < size){
        	char c = s.charAt(i);
        	switch(c){
        		case '(':
        		case '[':
        		case '{':
        			parentheses.push(String.valueOf(c));
        			break;
        		case ')':
        		case ']':
        		case '}':
        			if(parentheses.size()>0){
        				String last = parentheses.pop();
            			last += c;
            			System.out.println(last);
            			System.out.println(last == "()");
            			switch(last){
                        	case "()": break;
                        	case "[]": break;
                        	case "{}": break;
                        	default: return false;
            			}
        			}else return false; 
        			//if(last != "()" && last != "[]" && last != "{}") return false;
        			//break;	
        	}
        	i++; 
        }
        //System.out.println(parentheses.size());
        if(parentheses.size() > 0) return false;
        return true;
    }
    
    public static int removeDuplicates(int[] A) {
        int size = A.length;
        int i = 0;
        int j = 1;
        int num = 0;
        if(size < 2) return size;
        //please notice that this array is sorted array
        //It returns the length of array with unique elements, but the original array need to be changed also. 
        while(j < size){
            if(A[i] == A[j]){
                j++;
            }else{
                i++;
                A[i] = A[j];
                j++;
            }
        }
        A = Arrays.copyOf(A, i+1);
        return i+1;
    }
    
    public int removeElement(int[] A, int elem) {
        int size = A.length;
        int i = 0;
        while(i < size){
            if(A[i] != elem) i++;
            else{
                A[i] = A[size-1];
                size--;
            }
        }
        return size;
    }
    
    public int strStr(String haystack, String needle) {
        // int index = -1;
        // if(haystack.length() < needle.length()) return index;
        // int index = haystack.indexOf(needle);
        // return index;
        if(haystack.equals(needle)) return 0;
        for(int i = 0; i < haystack.length() - needle.length() + 1; i++){
            if(needle.equals(haystack.substring(i, i + needle.length()))) return i;
        }
        return -1;
    }
    
    public static int climbStairs(int n) {
//    	if(n < 2) return n;
//        int[] array = new int[n];
//        array[0] = 1;
//        array[1] = 2;
//        for(int i = 2; i < n; i++){
//        	array[i] = array[i-1] + array[i-2];
//        }
//		return array[n-1];
    	int x = 1;
        int y = 1;
        int z = 2;
        for(int i = 0; i < n; i++){
            x = y;
            y = z;
            z = x + y;
            
        }
        return x;
    }
    public static boolean wordBreak1(String s, Set<String> dict) {
        int size = s.length();
        int[] isWord = new int[size];
        if(dict.contains(s)) return true;
        for(int i = 0; i < size; i++){
        	int j = i; 
            while(j > 0){
            	String temp1 = s.substring(j, i+1);
            	String temp2 = s.substring(0, j);
            	if(dict.contains(temp1) && wordBreak1(temp2, dict)){
            		isWord[i] = 1;
            		break;
            	} 
        		else j--;
            }
        }
        //System.out.println(isWord);
		if(isWord[size - 1] == 1)return true;
		else return false;
    }
    
    public static boolean wordBreak(String s, Set<String> dict) {
    	//if(dict.contains(s)) return true;	 
    	String str = "*" + s;
        int size = str.length();
        System.out.println(size);
        int[] isWord = new int[size];
        isWord[0] = 1;
        for(int i = 1; i < size; i++){
        	for(int j = i-1; j >= 0; j--){
            	String temp1 = s.substring(j, i);
            	if(isWord[j] == 1 && dict.contains(temp1) ){
            		//System.out.println(temp1);
            		isWord[i] = 1;
            		break;
            	}          	
            }
        }
        for(int i = 1; i < size; i++){
        	System.out.println(isWord[i]);
        }
		if(isWord[size - 1] == 1)return true;
		else return false;
    }
    
    public static List<String> wordBreak2(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();
        if(s == null || dict == null)return words;
        wordBreak(s, dict,"",words);
		return words;
        
    }
    
	private static void wordBreak(String s, Set<String> dict, String str, List<String> words) {
		// TODO Auto-generated method stub
		int size = s.length();
		if(size == 0)
			words.add(str);
		for(int i = 0; i< size; i++){
			String temp1 = s.substring(0, i+1);
			String temp2 = s.substring(i+1, size);
			if(dict.contains(temp1)){
				str += " " + temp1;
				wordBreak(temp2,dict,str,words);
			}
				
		}
	}

	
	public static int uniquePaths(int m, int n) {
//        int[][] path = new int[m][n];
//        for(int i = 0; i < m; i++){
//            path[i][0] = 1;
//            path[i][n-1] = 1;
//        }
//            
//        for(int i = 0; i < n; i++){
//            path[0][i] = 1;
//            path[m-1][i] = 1;
//        }
//    
//        for(int i = 1; i < m; i++){
//            for(int j = 1; j < n; j++){
//                path[i][j] = path[i-1][j] + path[i][j-1];
//            }
//        }
//        return path[m-1][n-1];
		int[] path = new int[n];
		path[0] = 1;
		for(int i = 0; i < m; i++){
			for(int j = 1; j < n; j++){
				path[j] = path[j-1] + path[j];
			}
		}
		return path[n-1];
    }
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[] path = new int[n];
		if(obstacleGrid[0][0] == 1)return 0;
		path[0] = 1;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(obstacleGrid[i][j] == 1) path[j] = 0;
				else if(j > 0) path[j] = path[j-1] + path[j];
			}
		}
		return path[n-1];

    }

	public int minimumTotal(List<List<Integer>> triangle) {
		 int size = triangle.size();
	        int[] min = new int[size];
	        for(int i = 0; i < triangle.get(size-1).size(); i++){
	        	min[i] = triangle.get(size-1).get(i);
	        }
	        for(int i = size - 2; i >= 0; i--){
	        	List<Integer> row = triangle.get(i);
	        	for(int j = 0; j <  triangle.get(i + 1).size() - 1; j++){
	        		min[j] = row.get(j) + Math.min(min[j], min[j+1]);
	        	}
	        }
			return min[0];
    }
	
	public int minPathSum(int[][] grid) {
		int m = grid.length;
        int n = grid[0].length;
        int[][] path = new int[m][n];
        if(m == 0 && n == 0) return 0;
        path[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
        	path[i][0] = path[i-1][0] + grid[i][0];
        	path[i][n-1] = path[i-1][n-1] + grid[i][n-1];
        }
        for(int i = 1; i < n; i++){
        	path[0][i] = path[0][i-1] + grid[0][i];
        	path[m-1][i] = path[m-1][i-1] + grid[m-1][i];
        }
        for(int i = 1; i< m; i++){
        	for(int j = 1; j < n; j++){
        		path[i][j] = Math.min(path[i-1][j], path[i][j-1]) + grid[i][j];
        	}
        }
		return path[m-1][n-1];
    }
	
    public int maxProduct(int[] A) {
        int size = A.length;
        if(size == 0) return 0;
        int[] max = new int[size];
        max[0] = A[0];
        int maximum = max[0];
        int minimum = max[0];
        int product = max[0];
        for(int i = 1; i < size; i++){
            int num = maximum;
            maximum = Math.max(Math.max(A[i], A[i]*maximum), A[i]*minimum);
            minimum = Math.min(Math.min(A[i], A[i]*num), A[i]*minimum);
            product = Math.max(maximum, product);
        }
        return product;
    }
    
    public static int numDecodings(String s) {
        int size = s.length();
        if(size == 0)return 0;
        
        int[] decode = new int[size+1];
        if(s.charAt(0) == '0')return 0;
        decode[0] = 1;
        decode[1] = 1;
        int temp;
        for(int i = 2; i <= size; i++){
        	//actually start with the second character within the string
            temp = Integer.parseInt(s.substring(i-1, i));
            
            if(temp != 0)
            	decode[i] = decode[i-1];
            if(s.charAt(i-2) != '0'){
            	temp = Integer.parseInt(s.substring(i-2, i));
            	if(temp > 0 && temp < 27){
            		decode[i] += decode[i-2];
            	}
            }
        }
        return decode[size];
    }
    
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < minPrice)
                minPrice = prices[i];
            if(maxProfit < (prices[i] -minPrice))
                maxProfit = (prices[i] -minPrice);
        }
        return maxProfit;
    }
    
	public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Please enter a string: ");
//        String s = br.readLine();
//        System.out.print("Please enter an integer: ");
//        String nStr = br.readLine();
//        int n = Integer.parseInt(nStr);
//        String result = convert(s, nRows);
//        String result = longestPalindrome3(s);
//        int result = atoi(s);
//        System.out.print(result);
//        System.out.print(s.substring(0, 2));
        
        //int ar1[] = {1, 12, 15, 26, 38};
//        int ar2[] = {2, 13, 17, 30, 45};
		//int a = 13;
		//int[] result = twoSum(ar1,a);
       // System.out.print(result[0]);
        //System.out.print(result[1]);
        //int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        //List<Integer> result = new ArrayList<Integer>();
        //result = spiralOrder(matrix);
        //boolean result = isUnique("shdjkhgjka");
        //int result = romanToInt("MCMXCVI");
        //String result = intToRoman2(1);
        //String[] str = {"acc","aaa","aaba"};
        //String result = longestCommonPrefix(str);
        //boolean result = isValid("({}[])");
       //int[] a = {1,1,2,2,3,3};
        //int result = removeDuplicates(a);
        //int result = climbStairs(10);
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//        Set<String> dic = new HashSet<String>();
//        dic.add("aaaaaaaaaa");
//        dic.add("aaaaaaaaa");
//        dic.add("aaaaaaaa");
//        dic.add("aaaaaaa");
//        dic.add("aaaaaa");
//        dic.add("aaaaa");
//        dic.add("aaaa");
//        dic.add("aaa");
//        dic.add("aa");
//        dic.add("a");
//        dic.add("aaaa");
//        dic.add("aa");
//        List<String> result = wordBreak2(s, dic);
        //System.out.println(dic.contains("a"));
        //int result = uniquePaths(2, 3);
//        int[][] obstacleGrid = {{0,0},{1,1},{0,0}};
//        int result =  uniquePathsWithObstacles(obstacleGrid);
        int result = numDecodings("11");
        System.out.print(result);
        
    }
}
