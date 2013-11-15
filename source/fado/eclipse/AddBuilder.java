package fado.eclipse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import fado.eclipse.builder.FadoBuilder;
import fado.eclipse.builder.FadoNature;

//import fado.eclipse.builder.FadoBuilder;
//import fado.eclipse.builder.FadoNature;

public class AddBuilder extends AbstractHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) {
		final IProject project = getProject(event);

		if (project != null) {
			try {
				// verify already registered builders
				if (hasBuilder(project))
					// already enabled
					return null;

				// add builder to project properties
				IProjectDescription description = project.getDescription();
				final ICommand buildCommand = description.newCommand();
				buildCommand.setBuilderName(FadoBuilder.BUILDER_ID);

				final List<ICommand> commands = new ArrayList<ICommand>();
				commands.addAll(Arrays.asList(description.getBuildSpec()));
				commands.add(buildCommand);

				description.setBuildSpec(commands.toArray(new ICommand[commands.size()]));
				project.setDescription(description, null);

			} catch (final CoreException e) {
				// TODO could not read/write project description
				e.printStackTrace();
			}
		}

		return null;
	}

	public static IProject getProject(final ExecutionEvent event) {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final Object element = ((IStructuredSelection) selection).getFirstElement();

			return (IProject) Platform.getAdapterManager().getAdapter(element, IProject.class);
		}

		return null;
	}

	public static final boolean hasBuilder( final IProject project ) 
	{
		try 
		{
			IProjectDescription description = project.getDescription();
			String[] original = description.getNatureIds();

//			for (int i = 0; i < original.length; ++i) {
//				String n = original[i];
//				System.out.println( n );
//			}

			// Add the nature
			String[] copy = new String[ original.length + 1 ];
			System.arraycopy( original, 0, copy, 0, original.length );
			copy[ original.length ] = FadoNature.NATURE_ID;
			description.setNatureIds( copy );
			project.setDescription( description, null );
		} catch (CoreException e) {
		}

		try {
			{
				IProjectDescription desc = project.getDescription();
				ICommand[] original = desc.getBuildSpec();

//				for( int i = 0; i < original.length; ++i )
//				{
//					String name = original[i].getBuilderName();
//					System.out.println( name );
//				}

				ICommand[] copy = new ICommand[ original.length + 1 ];
				System.arraycopy( original, 0, copy, 0, original.length );
				ICommand command = desc.newCommand();
				command.setBuilderName( FadoBuilder.BUILDER_ID );
				copy[ copy.length - 1 ] = command;
				desc.setBuildSpec( copy );
				project.setDescription( desc, null );
			}

//			boolean yikes = project.isNatureEnabled( FadoNature.NATURE_ID );

			IProjectNature nature = project.getNature( FadoNature.NATURE_ID );
			for (final ICommand buildSpec : project.getDescription().getBuildSpec()) {
				if (FadoBuilder.BUILDER_ID.equals(buildSpec.getBuilderName()))
					return true;
			}
		} catch (final CoreException e) {
		}

		return false;
	}
}
