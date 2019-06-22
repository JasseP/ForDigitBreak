package ru.digitbreak.mobileapp.service;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler mainThread();

    Scheduler io();

    Scheduler computation();

    Scheduler immediate();
}
