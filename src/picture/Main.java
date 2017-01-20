package picture;

public class Main {

    public static void main(String[] args) {
        Process process = new Process(Utils.loadPicture(args[1]));
        Process process1 = new Process(Utils.loadPicture(args[2]));

        switch (args[0]) {
            case ("invert"):
                process.invert();
                Utils.savePicture(process.getPic(), args[args.length - 1]);
                break;
            case ("grayscale"):
                process.grayscale();
                Utils.savePicture(process.getPic(), args[args.length - 1]);
                break;
            case ("rotate"):
                process1.rotate(args[1]);
                Utils.savePicture(process1.getPic(), args[args.length - 1]);
                break;
            case ("flip"):
                process1.flip(args[1]);
                Utils.savePicture(process1.getPic(), args[args.length - 1]);
                break;
            case ("blend"):
                Picture[] arg = new Picture[args.length - 2];
                for (int i = 1; i < args.length - 1; i++) {
                    arg[i - 1] = Utils.loadPicture(args[i]);
                }
                process.blend(arg);
                Utils.savePicture(process.getPic(), args[args.length - 1]);
                break;
            case ("blur"):
                process.blur();
                Utils.savePicture(process.getPic(), args[args.length - 1]);
                break;
            default:
                process.getPic();
        }


//    System.err.println("TODO: Implement main");
    }

}
