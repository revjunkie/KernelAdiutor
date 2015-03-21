/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grarak.kerneladiutor.utils.kernel;

import android.content.Context;

import com.grarak.kerneladiutor.R;
import com.grarak.kerneladiutor.utils.Constants;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.root.Control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willi on 06.02.15.
 */
public class CPUHotplug implements Constants {

    private enum INTELLIPLUG_TYPE {
        INTELLIPLUG, INTELLIPLUG_5
    }

    private static INTELLIPLUG_TYPE TYPE;

    private static String MSM_HOTPLUG_ENABLE_FILE;
    private static String MSM_HOTPLUG_UPDATE_RATE_FILE;
    private static String MSM_HOTPLUG_IO_IS_BUSY_FILE;
    private static String MSM_HOTPLUG_SUSPEND_FREQ_FILE;

    public static void activateRevDebug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", REV_DEBUG, Control.CommandType.GENERIC, context);
    }

    public static boolean isRevDebug() {
        return Utils.readFile(REV_DEBUG).equals("1");
    }

    public static boolean hasRevDebug() {
        return Utils.existFile(REV_DEBUG);
    }

    public static void setRevMaxCpu(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_MAX_CPU, Control.CommandType.GENERIC, context);
    }

    public static int getRevMaxCpu() {
        return Utils.stringToInt(Utils.readFile(REV_MAX_CPU));
    }

    public static boolean hasRevMaxCpu() {
        return Utils.existFile(REV_MAX_CPU);
    }

    public static void setRevMinCpu(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_MIN_CPU, Control.CommandType.GENERIC, context);
    }

    public static int getRevMinCpu() {
        return Utils.stringToInt(Utils.readFile(REV_MIN_CPU));
    }

    public static boolean hasRevMinCpu() {
        return Utils.existFile(REV_MIN_CPU);
    }

    public static void setRevSamplingPeriod(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_SAMPLING_PERIOD, Control.CommandType.GENERIC, context);
    }

    public static int getRevSamplingPeriod() {
        return Utils.stringToInt(Utils.readFile(REV_SAMPLING_PERIOD));
    }

    public static boolean hasRevSamplingPeriod() {
        return Utils.existFile(REV_SAMPLING_PERIOD);
    }

    public static void setRevDownShiftThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_DOWN_SHIFT_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getRevDownShiftThreshold() {
        return Utils.stringToInt(Utils.readFile(REV_DOWN_SHIFT_THRESHOLD));
    }

    public static boolean hasRevDownShiftThreshold() {
        return Utils.existFile(REV_DOWN_SHIFT_THRESHOLD);
    }

    public static void setRevDownShift(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_DOWN_SHIFT, Control.CommandType.GENERIC, context);
    }

    public static int getRevDownShift() {
        return Utils.stringToInt(Utils.readFile(REV_DOWN_SHIFT));
    }

    public static boolean hasRevDownShift() {
        return Utils.existFile(REV_DOWN_SHIFT);
    }

    public static void setRevShiftAllThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_SHIFT_ALL_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getRevShiftAllThreshold() {
        return Utils.stringToInt(Utils.readFile(REV_SHIFT_ALL_THRESHOLD));
    }

    public static boolean hasRevShiftAllThreshold() {
        return Utils.existFile(REV_SHIFT_ALL_THRESHOLD);
    }

    public static void setRevShiftOneThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_SHIFT_ONE_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getRevShiftOneThreshold() {
        return Utils.stringToInt(Utils.readFile(REV_SHIFT_ONE_THRESHOLD));
    }

    public static boolean hasRevShiftOneThreshold() {
        return Utils.existFile(REV_SHIFT_ONE_THRESHOLD);
    }

    public static void setRevShiftAll(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_SHIFT_ALL, Control.CommandType.GENERIC, context);
    }

    public static int getRevShiftAll() {
        return Utils.stringToInt(Utils.readFile(REV_SHIFT_ALL));
    }

    public static boolean hasRevShiftAll() {
        return Utils.existFile(REV_SHIFT_ALL);
    }

    public static void setRevShiftOne(int value, Context context) {
        Control.runCommand(String.valueOf(value), REV_SHIFT_ONE, Control.CommandType.GENERIC, context);
    }

    public static int getRevShiftOne() {
        return Utils.stringToInt(Utils.readFile(REV_SHIFT_ONE));
    }

    public static boolean hasRevShiftOne() {
        return Utils.existFile(REV_SHIFT_ONE);
    }

    public static void activateRevHotplug(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", REV_HOTPLUG_ENABLE, Control.CommandType.GENERIC, context);
    }

    public static boolean isRevHotplugEnable() {
        return Utils.readFile(REV_HOTPLUG_ENABLE).equals("Y");
    }

    public static boolean hasRevHotplugEnable() {
        return Utils.existFile(REV_HOTPLUG_ENABLE);
    }

    public static boolean hasRevHotplug() {
        return Utils.existFile(REV_HOTPLUG);
    }

    public static void setMakoHotplugSuspendFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_SUSPEND_FREQ, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugSuspendFreq() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_SUSPEND_FREQ));
    }

    public static boolean hasMakoHotplugSuspendFreq() {
        return !Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ) && Utils.existFile(MAKO_HOTPLUG_SUSPEND_FREQ);
    }

    public static void setMakoHotplugTimer(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_TIMER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugTimer() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_TIMER));
    }

    public static boolean hasMakoHotplugTimer() {
        return Utils.existFile(MAKO_HOTPLUG_TIMER);
    }

    public static void setMakoHotplugMinTimeCpuOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugMinTimeCpuOnline() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE));
    }

    public static boolean hasMakoHotplugMinTimeCpuOnline() {
        return Utils.existFile(MAKO_HOTPLUG_MIN_TIME_CPU_ONLINE);
    }

    public static void setMakoHotplugMaxLoadCounter(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_MAX_LOAD_COUNTER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugMaxLoadCounter() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_MAX_LOAD_COUNTER));
    }

    public static boolean hasMakoHotplugMaxLoadCounter() {
        return Utils.existFile(MAKO_HOTPLUG_MAX_LOAD_COUNTER);
    }

    public static void setMakoHotplugLoadThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_LOAD_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugLoadThreshold() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_LOAD_THRESHOLD));
    }

    public static boolean hasMakoHotplugLoadThreshold() {
        return Utils.existFile(MAKO_HOTPLUG_LOAD_THRESHOLD);
    }

    public static void setMakoHotplugHighLoadCounter(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_HIGH_LOAD_COUNTER, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugHighLoadCounter() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_HIGH_LOAD_COUNTER));
    }

    public static boolean hasMakoHotplugHighLoadCounter() {
        return Utils.existFile(MAKO_HOTPLUG_HIGH_LOAD_COUNTER);
    }

    public static void setMakoHotplugFirstLevel(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_FIRST_LEVEL, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugFirstLevel() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_FIRST_LEVEL));
    }

    public static boolean hasMakoHotplugFirstLevel() {
        return Utils.existFile(MAKO_HOTPLUG_FIRST_LEVEL);
    }

    public static void setMakoHotplugCpuFreqUnplugLimit(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugCpuFreqUnplugLimit() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT));
    }

    public static boolean hasMakoHotplugCpuFreqUnplugLimit() {
        return Utils.existFile(MAKO_HOTPLUG_CPUFREQ_UNPLUG_LIMIT);
    }

    public static void setMakoHotplugCoresOnTouch(int value, Context context) {
        Control.runCommand(String.valueOf(value), MAKO_HOTPLUG_CORES_ON_TOUCH, Control.CommandType.GENERIC, context);
    }

    public static int getMakoHotplugCoresOnTouch() {
        return Utils.stringToInt(Utils.readFile(MAKO_HOTPLUG_CORES_ON_TOUCH));
    }

    public static boolean hasMakoHotplugCoresOnTouch() {
        return Utils.existFile(MAKO_HOTPLUG_CORES_ON_TOUCH);
    }

    public static void activateMakoHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MAKO_HOTPLUG_ENABLED, Control.CommandType.GENERIC, context);
    }

    public static boolean isMakoHotplugActive() {
        return Utils.readFile(MAKO_HOTPLUG_ENABLED).equals("1");
    }

    public static boolean hasMakoHotplugEnable() {
        return Utils.existFile(MAKO_HOTPLUG_ENABLED);
    }

    public static boolean hasMakoHotplug() {
        return Utils.existFile(MAKO_HOTPLUG);
    }

    public static void setMsmHotplugSuspendDeferTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_SUSPEND_DEFER_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendDeferTime() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_SUSPEND_DEFER_TIME));
    }

    public static boolean hasMsmHotplugSuspendDeferTime() {
        return Utils.existFile(HOTPLUG_MSM_SUSPEND_DEFER_TIME);
    }

    public static void setMsmHotplugSuspendFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_HOTPLUG_SUSPEND_FREQ_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendFreq() {
        return Utils.stringToInt(Utils.readFile(MSM_HOTPLUG_SUSPEND_FREQ_FILE));
    }

    public static boolean hasMsmHotplugSuspendFreq() {
        if (!Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ))
            if (Utils.existFile(HOTPLUG_MSM_SUSPEND_FREQ)) {
                MSM_HOTPLUG_SUSPEND_FREQ_FILE = HOTPLUG_MSM_SUSPEND_FREQ;
            } else if (Utils.existFile(HOTPLUG_MSM_SUSPEND_MAX_FREQ))
                MSM_HOTPLUG_SUSPEND_FREQ_FILE = HOTPLUG_MSM_SUSPEND_MAX_FREQ;
        return MSM_HOTPLUG_SUSPEND_FREQ_FILE != null;
    }

    public static void setMsmHotplugSuspendMaxCpus(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_SUSPEND_MAX_CPUS, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugSuspendMaxCpus() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_SUSPEND_MAX_CPUS));
    }

    public static boolean hasMsmHotplugSuspendMaxCpus() {
        return Utils.existFile(HOTPLUG_MSM_SUSPEND_MAX_CPUS);
    }

    public static void activateMsmHotplugIoIsBusy(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MSM_HOTPLUG_IO_IS_BUSY_FILE, Control.CommandType.GENERIC, context);
    }

    public static boolean isMsmHotplugIoIsBusyActive() {
        return Utils.readFile(MSM_HOTPLUG_IO_IS_BUSY_FILE).equals("1");
    }

    public static boolean hasMsmHotplugIoIsBusy() {
        if (Utils.existFile(HOTPLUG_MSM_IO_IS_BUSY))
            MSM_HOTPLUG_IO_IS_BUSY_FILE = HOTPLUG_MSM_IO_IS_BUSY;
        else if (Utils.existFile(HOTPLUG_MSM_HP_IO_IS_BUSY))
            MSM_HOTPLUG_IO_IS_BUSY_FILE = HOTPLUG_MSM_HP_IO_IS_BUSY;
        return MSM_HOTPLUG_IO_IS_BUSY_FILE != null;
    }

    public static void setMsmHotplugOfflineLoad(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_OFFLINE_LOAD, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugOfflineLoad() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_OFFLINE_LOAD));
    }

    public static boolean hasMsmHotplugOfflineLoad() {
        return Utils.existFile(HOTPLUG_MSM_OFFLINE_LOAD);
    }

    public static void setMsmHotplugFastLaneMinFreq(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_FAST_LANE_MIN_FREQ, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugFastLaneMinFreq() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_FAST_LANE_MIN_FREQ));
    }

    public static boolean hasMsmHotplugFastLaneMinFreq() {
        return Utils.existFile(HOTPLUG_MSM_FAST_LANE_MIN_FREQ);
    }

    public static void setMsmHotplugFastLaneLoad(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_FAST_LANE_LOAD, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugFastLaneLoad() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_FAST_LANE_LOAD));
    }

    public static boolean hasMsmHotplugFastLaneLoad() {
        return Utils.existFile(HOTPLUG_MSM_FAST_LANE_LOAD);
    }

    public static void setMsmHotplugUpdateRate(int value, Context context) {
        Control.runCommand(String.valueOf(value), MSM_HOTPLUG_UPDATE_RATE_FILE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugUpdateRate() {
        return Utils.stringToInt(Utils.readFile(MSM_HOTPLUG_UPDATE_RATE_FILE));
    }

    public static boolean hasMsmHotplugUpdateRate() {
        if (Utils.existFile(HOTPLUG_MSM_UPDATE_RATE))
            MSM_HOTPLUG_UPDATE_RATE_FILE = HOTPLUG_MSM_UPDATE_RATE;
        else if (Utils.existFile(HOTPLUG_MSM_UPDATE_RATES))
            MSM_HOTPLUG_UPDATE_RATE_FILE = HOTPLUG_MSM_UPDATE_RATES;
        return MSM_HOTPLUG_UPDATE_RATE_FILE != null;
    }

    public static void setMsmHotplugHistorySize(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_HISTORY_SIZE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugHistorySize() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_HISTORY_SIZE));
    }

    public static boolean hasMsmHotplugHistorySize() {
        return Utils.existFile(HOTPLUG_MSM_HISTORY_SIZE);
    }

    public static void setMsmHotplugDownLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_DOWN_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugDownLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_DOWN_LOCK_DURATION));
    }

    public static boolean hasMsmHotplugDownLockDuration() {
        return Utils.existFile(HOTPLUG_MSM_DOWN_LOCK_DURATION);
    }

    public static void setMsmHotplugBoostLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_BOOST_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugBoostLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_BOOST_LOCK_DURATION));
    }

    public static boolean hasMsmHotplugBoostLockDuration() {
        return Utils.existFile(HOTPLUG_MSM_BOOST_LOCK_DURATION);
    }

    public static void setMsmHotplugMaxCpusOnlineSusp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMaxCpusOnlineSusp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP));
    }

    public static boolean hasMsmHotplugMaxCpusOnlineSusp() {
        return Utils.existFile(HOTPLUG_MSM_MAX_CPUS_ONLINE_SUSP);
    }

    public static void setMsmHotplugCpusBoosted(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_CPUS_BOOSTED, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugCpusBoosted() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_CPUS_BOOSTED));
    }

    public static boolean hasMsmHotplugCpusBoosted() {
        return Utils.existFile(HOTPLUG_MSM_CPUS_BOOSTED);
    }

    public static void setMsmHotplugMaxCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMaxCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MAX_CPUS_ONLINE));
    }

    public static boolean hasMsmHotplugMaxCpusOnline() {
        return Utils.existFile(HOTPLUG_MSM_MAX_CPUS_ONLINE);
    }

    public static void setMsmHotplugMinCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_MSM_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getMsmHotplugMinCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_MSM_MIN_CPUS_ONLINE));
    }

    public static boolean hasMsmHotplugMinCpusOnline() {
        return Utils.existFile(HOTPLUG_MSM_MIN_CPUS_ONLINE);
    }

    public static void activateMsmHotplugDebugMask(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_MSM_DEBUG_MASK, Control.CommandType.GENERIC, context);
    }

    public static boolean isMsmHotplugDebugMaskActive() {
        return Utils.readFile(HOTPLUG_MSM_DEBUG_MASK).equals("1");
    }

    public static boolean hasMsmHotplugDebugMask() {
        return Utils.existFile(HOTPLUG_MSM_DEBUG_MASK);
    }

    public static void activateMsmHotplug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", MSM_HOTPLUG_ENABLE_FILE, Control.CommandType.GENERIC, context);
    }

    public static boolean isMsmHotplugActive() {
        return Utils.readFile(MSM_HOTPLUG_ENABLE_FILE).equals("1");
    }

    public static boolean hasMsmHotplugEnable() {
        if (Utils.existFile(HOTPLUG_MSM_ENABLE)) MSM_HOTPLUG_ENABLE_FILE = HOTPLUG_MSM_ENABLE;
        else if (Utils.existFile(HOTPLUG_MSM_ENABLE_2))
            MSM_HOTPLUG_ENABLE_FILE = HOTPLUG_MSM_ENABLE_2;
        return MSM_HOTPLUG_ENABLE_FILE != null;
    }

    public static boolean hasMsmHotplug() {
        return Utils.existFile(HOTPLUG_MSM);
    }

    public static void setBluPlugDownTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugDownTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT));
    }

    public static boolean hasBluPlugDownTimerCnt() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_DOWN_TIMER_CNT);
    }

    public static void setBluPlugUpTimerCnt(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_UP_TIMER_CNT, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugUpTimerCnt() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_UP_TIMER_CNT));
    }

    public static boolean hasBluPlugUpTimerCnt() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_UP_TIMER_CNT);
    }

    public static void setBluPlugUpThreshold(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_UP_THRESHOLD, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugUpThreshold() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_UP_THRESHOLD));
    }

    public static boolean hasBluPlugUpThreshold() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_UP_THRESHOLD);
    }

    public static void setBluPlugMaxFreqScreenOff(int position, Context context) {
        String command = position == 0 ? "0" : String.valueOf(CPU.getFreqs().get(position - 1));
        Control.runCommand(command, HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxFreqScreenOff() {
        String value = Utils.readFile(HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF);
        if (value.equals("0")) return 0;
        return CPU.getFreqs().indexOf(Utils.stringToInt(value)) + 1;
    }

    public static boolean hasBluPlugMaxFreqScreenOff() {
        return !Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ) && Utils.existFile(HOTPLUG_BLU_PLUG_MAX_FREQ_SCREEN_OFF);
    }

    public static void setBluPlugMaxCoresScreenOff(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxCoresScreenOff() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF));
    }

    public static boolean hasBluPlugMaxCoresScreenOff() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MAX_CORES_SCREEN_OFF);
    }

    public static void setBluPlugMaxOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MAX_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMaxOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MAX_ONLINE));
    }

    public static boolean hasBluPlugMaxOnline() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MAX_ONLINE);
    }

    public static void setBluPlugMinOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_BLU_PLUG_MIN_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getBluPlugMinOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_BLU_PLUG_MIN_ONLINE));
    }

    public static boolean hasBluPlugMinOnline() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_MIN_ONLINE);
    }

    public static void activateBluPlugPowersaverMode(boolean active, Context context) {
        Control.runCommand(active ? "Y" : "N", HOTPLUG_BLU_PLUG_POWERSAVER_MODE, Control.CommandType.GENERIC, context);
    }

    public static boolean isBluPlugPowersaverModeActive() {
        return Utils.readFile(HOTPLUG_BLU_PLUG_POWERSAVER_MODE).equals("Y");
    }

    public static boolean hasBluPlugPowersaverMode() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_POWERSAVER_MODE);
    }

    public static void activateBluPlug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_BLU_PLUG_ENABLE, Control.CommandType.GENERIC, context);
    }

    public static boolean isBluPlugActive() {
        return Utils.readFile(HOTPLUG_BLU_PLUG_ENABLE).equals("1");
    }

    public static boolean hasBluPlugEnable() {
        return Utils.existFile(HOTPLUG_BLU_PLUG_ENABLE);
    }

    public static boolean hasBluPlug() {
        return Utils.existFile(HOTPLUG_BLU_PLUG);
    }

    public static void setIntelliPlugFShift(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_FSHIFT, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugFShift() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_FSHIFT));
    }

    public static boolean hasIntelliPlugFShift() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_FSHIFT);
    }

    public static void setIntelliPlugDownLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugDownLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION));
    }

    public static boolean hasIntelliPlugDownLockDuration() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DOWN_LOCK_DURATION);
    }

    public static void setIntelliPlugBoostLockDuration(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugBoostLockDuration() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION));
    }

    public static boolean hasIntelliPlugBoostLockDuration() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_BOOST_LOCK_DURATION);
    }

    public static void setIntelliPlugDeferSampling(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugDeferSampling() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING));
    }

    public static boolean hasIntelliPlugDeferSampling() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DEFER_SAMPLING);
    }

    public static void setIntelliPlugSuspendDeferTime(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugSuspendDeferTime() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME));
    }

    public static boolean hasIntelliPlugSuspendDeferTime() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND_DEFER_TIME);
    }

    public static void setIntelliPlugMaxCpusOnlineSusp(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMaxCpusOnlineSusp() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP));
    }

    public static boolean hasIntelliPlugMaxCpusOnlineSusp() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE_SUSP);
    }

    public static void setIntelliPlugMaxCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMaxCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE));
    }

    public static boolean hasIntelliPlugMaxCpusOnline() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MAX_CPUS_ONLINE);
    }

    public static void setIntelliPlugMinCpusOnline(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugMinCpusOnline() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE));
    }

    public static boolean hasIntelliPlugMinCpusOnline() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_MIN_CPUS_ONLINE);
    }

    public static void setIntelliPlugCpusBoosted(int value, Context context) {
        Control.runCommand(String.valueOf(value), HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugCpusBoosted() {
        return Utils.stringToInt(Utils.readFile(HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED));
    }

    public static boolean hasIntelliPlugCpusBoosted() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_CPUS_BOOSTED);
    }

    public static void activateIntelliPlugSuspend(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_5_SUSPEND, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugSuspendActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND).equals("1");
    }

    public static boolean hasIntelliPlugSuspend() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_SUSPEND);
    }

    public static void activateIntelliPlugDebug(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_5_DEBUG, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugDebugActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_5_DEBUG).equals("1");
    }

    public static boolean hasIntelliPlugDebug() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_5_DEBUG);
    }

    public static void setIntelliPlugScreenOffMax(int position, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;

        String command = position == 0 ? "4294967295" : String.valueOf(CPU.getFreqs().get(position - 1));
        Control.runCommand(command, file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugScreenOffMax() {
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;

        String value = Utils.readFile(file);
        if (value.equals("4294967295") // 4294967295 is 32-Bit max unsigned integer
                || value.equals("0")) return 0;
        return CPU.getFreqs().indexOf(Utils.stringToInt(value)) + 1;
    }

    public static boolean hasIntelliPlugScreenOffMax() {
        if (Utils.existFile(CPU_MAX_SCREEN_OFF_FREQ)) return false;
        String file = HOTPLUG_INTELLI_PLUG_SCREEN_OFF_MAX;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_SCREEN_OFF_MAX;
        return Utils.existFile(file);
    }

    public static void setIntelliPlugThresold(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugThresold() {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static boolean hasIntelliPlugThresold() {
        String file = HOTPLUG_INTELLI_PLUG_THRESHOLD;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_THRESHOLD;
        return Utils.existFile(file);
    }

    public static void setIntelliPlugHysteresis(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugHysteresis() {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static boolean hasIntelliPlugHysteresis() {
        String file = HOTPLUG_INTELLI_PLUG_HYSTERESIS;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_HYSTERESIS;
        return Utils.existFile(file);
    }

    public static void activateIntelliPlugTouchBoost(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_TOUCH_BOOST, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugTouchBoostActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_TOUCH_BOOST).equals("1");
    }

    public static boolean hasIntelliPlugTouchBoost() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_TOUCH_BOOST);
    }

    public static void activateIntelliPlugEco(boolean active, Context context) {
        Control.runCommand(active ? "1" : "0", HOTPLUG_INTELLI_PLUG_ECO, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugEcoActive() {
        return Utils.readFile(HOTPLUG_INTELLI_PLUG_ECO).equals("1");
    }

    public static boolean hasIntelliPlugEco() {
        return Utils.existFile(HOTPLUG_INTELLI_PLUG_ECO);
    }

    public static void setIntelliPlugProfile(int value, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        Control.runCommand(String.valueOf(value), file, Control.CommandType.GENERIC, context);
    }

    public static int getIntelliPlugProfile() {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        return Utils.stringToInt(Utils.readFile(file));
    }

    public static List<String> getIntelliPlugProfileMenu(Context context) {
        List<String> list = new ArrayList<>();
        list.add(context.getString(R.string.balanced));
        list.add(context.getString(R.string.performance));
        list.add(context.getString(R.string.conservative));
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) {
            list.add(context.getString(R.string.disabled));
            list.add(context.getString(R.string.tri));
            list.add(context.getString(R.string.eco));
            list.add(context.getString(R.string.strict));
        } else {
            list.add(context.getString(R.string.eco_performance));
            list.add(context.getString(R.string.eco_conservative));
        }
        return list;
    }

    public static boolean hasIntelliPlugProfile() {
        String file = HOTPLUG_INTELLI_PLUG_PROFILE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_PROFILE;
        return Utils.existFile(file);
    }

    public static void activateIntelliPlug(boolean active, Context context) {
        String file = HOTPLUG_INTELLI_PLUG_ENABLE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_ENABLE;
        Control.runCommand(active ? "1" : "0", file, Control.CommandType.GENERIC, context);
    }

    public static boolean isIntelliPlugActive() {
        String file = HOTPLUG_INTELLI_PLUG_ENABLE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_ENABLE;
        return Utils.readFile(file).equals("1");
    }

    public static boolean hasIntelliPlugEnable() {
        String file = HOTPLUG_INTELLI_PLUG_ENABLE;
        if (TYPE == INTELLIPLUG_TYPE.INTELLIPLUG_5) file = HOTPLUG_INTELLI_PLUG_5_ENABLE;
        return Utils.existFile(file);
    }

    public static boolean hasIntelliPlug() {
        if (Utils.existFile(HOTPLUG_INTELLI_PLUG)) TYPE = INTELLIPLUG_TYPE.INTELLIPLUG;
        if (Utils.existFile(HOTPLUG_INTELLI_PLUG_5)) TYPE = INTELLIPLUG_TYPE.INTELLIPLUG_5;
        return TYPE != null;
    }

    public static void activateMpdecision(boolean active, Context context) {
        if (active) Control.startService(HOTPLUG_MPDEC, true, context);
        else Control.stopService(HOTPLUG_MPDEC, true, context);
    }

    public static boolean isMpdecisionActive() {
        return Utils.isServiceActive(HOTPLUG_MPDEC);
    }

    public static boolean hasMpdecision() {
        return Utils.existFile(HOTPLUG_MPDECISION_BINARY);
    }

    public static boolean hasCpuHotplug() {
        for (String[] array : CPU_HOTPLUG_ARRAY)
            for (String file : array) if (Utils.existFile(file)) return true;
        return false;
    }

}
