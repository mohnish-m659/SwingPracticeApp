package clinicApp.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clinicApp.ui.views.AppointmentView;
import clinicApp.ui.views.IView;
import clinicApp.ui.views.PatientView;

public class ViewResolver {
	
	private static JFrame root;
	
	
	public enum View implements IViewEnum {
		PATIENT {

			@Override
			public JPanel getContent() {
				// TODO Auto-generated method stub
				return (JPanel) new PatientView();
			}

		},
		PHYSICIAN {

			@Override
			public JPanel getContent() {
				// TODO Auto-generated method stub
				return null;
			}

		},
		APPOINTMENT {

			@Override
			public JPanel getContent() {
				// TODO Auto-generated method stub
				return (JPanel) new AppointmentView();
			}

		};
	}
	
	public static void setRoot(JFrame root) {
		ViewResolver.root = root;
	}

	public static void setAppointmentView() {
		
	}

	static JList<View> viewList;
	static JPanel viewPanel;
	
	public static void setDefaultView() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		viewList = new JList<ViewResolver.View>(createViewModel());
		viewList.setSelectedIndex(0);
		viewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listScrollPane = new JScrollPane(viewList);
        listScrollPane.setPreferredSize(new Dimension(150, 0));
        
        viewPanel = new JPanel(new CardLayout());
        initViewPanel();
        
        viewList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					setView();
				}
			}
		});
        
        mainPanel.add(listScrollPane, BorderLayout.WEST);
        mainPanel.add(viewPanel, BorderLayout.CENTER);
        
        setView();
        root.setContentPane(mainPanel);
		
	}
	
	private static void initViewPanel() {
		for(int i=0; i<View.values().length; i++) {
			View view = viewList.getModel().getElementAt(i);
			viewPanel.add(getViewContent(view), view.toString());
		}
	}

	private static Component getViewContent(View view) {
		JPanel panel = view.getContent();
		if(panel == null) {
			return genericView();
		}
		return panel;
	}

	protected static void setView() {
		CardLayout cardLayout = (CardLayout) viewPanel.getLayout();
        cardLayout.show(viewPanel, viewList.getSelectedValue().toString());
	}

	protected static JPanel genericView() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sorry This view is not yet implemented");
		panel.add(label, JLabel.CENTER);
		return panel;
	}

	private static ListModel<View> createViewModel() {
		ListModel<View> model = new ListModel<ViewResolver.View>() {
			
			@Override
			public void removeListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return View.values().length;
			}
			
			@Override
			public View getElementAt(int index) {
				// TODO Auto-generated method stub
				return View.values()[index];
			}
			
			@Override
			public void addListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
			}
			
		};
		return model;
	}

	protected static JFrame getRoot() {
		return root;
	}

	public static void refresh() {
		for(int i=0; i<View.values().length; i++) {
			View view = viewList.getModel().getElementAt(i);
			IView content = (IView) view.getContent();
			if(content != null) {
				content.refresh();
			}
		}
	}

}
