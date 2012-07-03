package fado.eclipse.builder;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class FadoNature implements IProjectNature
{
	public static final String NATURE_ID = "fado.eclipse.fadoNature";

	private IProject _project;

	/*
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException
	{
		IProjectDescription desc = _project.getDescription();
		ICommand[] commands = desc.getBuildSpec();

		for( int i = 0; i < commands.length; ++i )
		{
			if( commands[i].getBuilderName().equals( FadoBuilder.BUILDER_ID ) )
			{
				return;
			}
		}

		ICommand[] newCommands = new ICommand[commands.length + 1];
		System.arraycopy( commands, 0, newCommands, 0, commands.length );
		ICommand command = desc.newCommand();
		command.setBuilderName( FadoBuilder.BUILDER_ID );
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec( newCommands );
		_project.setDescription( desc, null );
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException
	{
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		for( int i = 0; i < commands.length; ++i )
		{
			if( commands[i].getBuilderName().equals( FadoBuilder.BUILDER_ID ) )
			{
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy( commands, 0, newCommands, 0, i );
				System.arraycopy( commands, i + 1, newCommands, i, commands.length - i - 1 );
				description.setBuildSpec( newCommands );
				_project.setDescription( description, null );
				return;
			}
		}
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	public IProject getProject()
	{
		return _project;
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject( IProject project )
	{
		_project = project;
	}

}
