package kpo.restorant.services;

import jakarta.transaction.Transactional;
import kpo.restorant.models.Process;
import kpo.restorant.models.Status;
import kpo.restorant.models.User;
import kpo.restorant.repositories.ProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
    @Autowired
    private ProcessRepository processRepository;

    public void handleOrder(Process process) {
        OrderHandler handler = new OrderHandler(process, processRepository);
        handler.start();
    }

    @AllArgsConstructor
    static class OrderHandler extends Thread {
        private Process process;
        private ProcessRepository processRepository;

        public void run() {
            try {
                Thread.sleep(process.getTotalTime() * 1000);
            } catch (InterruptedException e) {
                // this should never happen
            }

            setReadyIfNotCanceled();
        }

        @Transactional
        public void setReadyIfNotCanceled() {
            process = processRepository.findById(process.getId()).get();
            if (process.getStatus() != Status.CANCELED) {
                process.setStatus(Status.READY);
                processRepository.save(process);
            }
        }
    }
}
