size = 10^6;

QAM=16;
SNR =20;
stream=randi([0 QAM-1], size, 1);
mod = qammod(stream, QAM);
signal_noise=awgn(mod, SNR, 'measured');
const=comm.ConstellationDiagram('ShowReferenceConstellation',false, 'XLimits', [-4 4], 'YLimits', [-4 4]);
step(const, signal_noise);  
hold on;