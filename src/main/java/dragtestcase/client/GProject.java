package dragtestcase.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;

public class GProject implements EntryPoint {
  @Override
  public void onModuleLoad() {
    List<Integer> tickets = new ArrayList<Integer>();
    for (int a = 0; a < 20; a++)
      tickets.add(a);
    RootLayoutPanel.get().add(new ScrollPanel(new Board(tickets)));
  }

  public class Board extends AbsolutePanel {
    private final PickupDragController dragController;

    public Board(final List<Integer> tickets) {
      setWidth("1000px");
      setHeight("1000px");

      dragController = new PickupDragController(this, false);
      dragController.setBehaviorConstrainedToBoundaryPanel(true);

      dragController.unregisterDropControllers();

      dragController.registerDropController(new AbsolutePositionDropController(this));

      for (final Integer ticket : tickets) {
        add(button(ticket), 100, ticket * 100);
      }
    }

    private Widget button(final Integer ticket) {
      final Widget button = new Button(String.valueOf(ticket));
      dragController.makeDraggable(button);
      return button;
    }
  }
}