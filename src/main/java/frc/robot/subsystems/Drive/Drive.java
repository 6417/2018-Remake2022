package frc.robot.subsystems.Drive;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.I2C;

public class Drive extends DriveBase {
    public static class Constants {
        public static class Motors {
            public static class Front {
                public static class Right {
                    public static final int motorRotationId = 4;
                    public static final int motorSpeedId = 7;
                    public static final I2C.Port encoderRotationPort = I2C.Port.kOnboard;
                    public static final Translation2d moduleLocation = new Translation2d(0.0, 0.0);
                    public static final byte encoderRotationDeviceAddress = 1;
                    public static final byte homePoint = 67;
                }

                public static class Left {
                    public static final I2C.Port encoderRotationPort = I2C.Port.kOnboard;
                    public static final int motorSpeedId = 6;
                    public static final int motorRotationId = 5;
                    public static final Translation2d moduleLocation = new Translation2d(0.0, 0.0);
                    public static final byte encoderRotationDeviceAddress = 4;
                    public static final byte homePoint = 77;
                }
            }

            public static class Back {
                public static class Right {
                    public static final int motorSpeedId = 11;
                    public static final int motorRotationId = 2;
                    public static final I2C.Port encoderRotationPort = I2C.Port.kOnboard;
                    public static final Translation2d moduleLocation = new Translation2d(0.0, 0.0);
                    public static final byte encoderRotationDeviceAddress = 2;
                    public static final byte homePoint = 123;
                }

                public static class Left {
                    public static final int motorSpeedId = 8;
                    public static final int motorRotationId = 10;
                    public static final I2C.Port encoderRotationPort = I2C.Port.kOnboard;
                    public static final Translation2d moduleLocation = new Translation2d(0.0, 0.0);
                    public static final byte encoderRotationDeviceAddress = 3;
                    public static final byte homePoint = 16;
                }
            }
        } 
    }

    private static DriveBase instance;
    private static boolean enable;

    public static DriveBase getInstance() {
        if (instance == null) {
            if (enable) {
                instance = new Drive();
            } else {
                instance = new DriveBase();
            }
        }
        return instance;
    }

    
}
