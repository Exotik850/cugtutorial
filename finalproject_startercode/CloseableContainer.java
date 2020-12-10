public class CloseableContainer extends Container implements Closeable {
    private Boolean isOpen;

    public CloseableContainer(World world, String name, String description, Boolean isOpen) {
        super(world, name, description);
        this.isOpen = isOpen;
    }

    public CloseableContainer(World world, String name, int weight, boolean takeable, String description, Boolean isOpen) {
        super(world, name, weight, takeable, description);
        this.isOpen = isOpen;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void doOpen() {
        if(isOpen){
            World.print("Opened.\n\n");
        } else {
            isOpen = Closeable.OPEN;
            World.print("Done.\n\n");
        }
    }

    @Override
    public void doClose() {
        if(!isOpen){
            World.print("Closed.\n\n");
        } else {
            isOpen = Closeable.CLOSED;
            World.print("Done.\n\n");
        }
    }

    @Override
    public void doExamine() {
        if (!isOpen) {
            World.print("The " + getName() + " is closed. \n\n");
        } else {
            World.print("Inside the " + getName() + " you see " + getItemString() + ".\n\n");
        }
    }

    @Override
    public Item doTake(Item item) {
        if (!isOpen) {
            World.print("The " + getName() + " is closed. \n\n");
        } else {
            super.doTake(item);
        }
        return null;
    }

    @Override
    public Item doPut(Item item, Container source){
        if (!isOpen) {
            World.print("The " + getName() + " is closed. \n\n");
        } else {
            super.doPut(item, source);
        }
        return null;
    }
}