package com.study.LL.healthcare.avgPace;

import org.springframework.stereotype.Service;

@Service
public class AvgPaceService {
    public AvgPaceData excute(float distance, float time) {
        AvgPaceData avgPaceData = new AvgPaceData();

        String ans = calc(distance, time);
        avgPaceData.setAns(ans);

        return avgPaceData;
    }

    private String calc(float distance, float time) {
        Float avg = time / distance;
        String ans = String.format("%.2f", avg);
        return ans;
    }
}
