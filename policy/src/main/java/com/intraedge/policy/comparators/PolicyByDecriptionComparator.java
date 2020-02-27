package com.intraedge.policy.comparators;

import java.util.Comparator;
import org.springframework.stereotype.Component;
import com.intraedge.policy.dto.Policy;
/**
 * Compares policies by description 
 * @author sbawaskar
 *
 */
@Component("policyByVersionComparator")
public class PolicyByDecriptionComparator implements Comparator<Policy>{
	@Override
	public int compare(Policy policy1, Policy policy2) {
		if(policy2.getDescription().equalsIgnoreCase(policy1.getDescription())){
			return Boolean.toString(policy1.isAccepted()).compareTo(Boolean.toString(policy2.isAccepted()));
		}else{
			return policy2.getDescription().compareToIgnoreCase(policy1.getDescription());
		}
	}
}
